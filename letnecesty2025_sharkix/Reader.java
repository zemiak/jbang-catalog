package letnecesty2025;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Reader {
    boolean loadError = false;
    boolean isProjectGC;
    String cacherName;
    String gpxDateTime;
    List<Geocache> geocaches = new ArrayList<>();

    final static LocalDate START_DATE = LocalDate.of(2025, 7, 1);
    final static LocalDate END_DATE = LocalDate.of(2025, 8, 31);

    public List<Geocache> read(String fileName) throws ParserConfigurationException, SAXException, IOException {
        InputStream inputStream = null;
        this.geocaches.clear();

        if (Path.of(fileName).toFile().isFile()) {
            inputStream = new FileInputStream(fileName);
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);

        inputStream.close();

        doc.getDocumentElement().normalize();
        isProjectGC = isProjectGC(doc);

        NodeList nodeList = doc.getElementsByTagName("wpt");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Element cache = (Element) element.getElementsByTagName("groundspeak:cache").item(0);
                if (null == cache) {
                    throw new IllegalStateException("Cache is missing from wpt " + node);
                }

                readOneCache(element);
            }
        }

        this.fillGeostatsDataInfo(doc);

        return geocaches;
    }

    private void fillGeostatsDataInfo(Document doc) {
        NodeList nodeList = doc.getElementsByTagName("gpx");
        if (nodeList.getLength() != 1) {
            throw new IllegalStateException("Cannot find the root element");
        }

        if (nodeList.item(0).getNodeType() != Node.ELEMENT_NODE) {
            throw new IllegalStateException("Root node is not an element");
        }

        Element root = (Element) nodeList.item(0);
        Node timeNode = root.getElementsByTagName("time").item(0);
        this.gpxDateTime = timeNode.getTextContent();
    }

    public void setErrorState(Throwable e) {
        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        printWriter.close();

        try {
            stringWriter.close();
        } catch (Exception e1) {
            // pass on
        }

        this.loadError = true;
    }

    public boolean getLoadError() {
        return this.loadError;
    }

    private boolean isProjectGC(Document doc) {
        NodeList nodeList = doc.getElementsByTagName("gpx");
        if (nodeList.getLength() != 1) {
            throw new IllegalStateException("Cannot find the root element");
        }

        if (nodeList.item(0).getNodeType() != Node.ELEMENT_NODE) {
            throw new IllegalStateException("Root node is not an element");
        }

        Element root = (Element) nodeList.item(0);
        Node nameNode = root.getElementsByTagName("name").item(0);
        return "Project-GC".equalsIgnoreCase(nameNode.getTextContent());
    }

    public String getCacherName() {
        return "sharkix";
    }

    private void readOneCache(Element rootElement) {
        Element cacheElement = (Element) rootElement.getElementsByTagName("groundspeak:cache").item(0);

        var code = rootElement.getElementsByTagName("name").item(0).getTextContent().toUpperCase();
        var cache = new Geocache();

        cache.lat = rootElement.getAttribute("lat");
        cache.lon = rootElement.getAttribute("lon");

        var log = new LogHandler(cacheElement, isProjectGC);
        this.cacherName = log.cacherName;
        cache.logDateTime = log.date;

        LocalDate logDate = log.date.toLocalDate();
        if (logDate.isBefore(START_DATE) || logDate.isAfter(END_DATE)) {
            // skipping FI outside of the date range
            return;
        }

        cache.logText = log.text;

        cache.size = cacheElement.getElementsByTagName("groundspeak:container").item(0).getTextContent();
        cache.type = cacheElement.getElementsByTagName("groundspeak:type").item(0).getTextContent();
        cache.code = code;
        cache.name = rootElement.getElementsByTagName("urlname").item(0).getTextContent();
        cache.longName = rootElement.getElementsByTagName("desc").item(0).getTextContent();
        cache.difficulty = Double.parseDouble(cacheElement.getElementsByTagName("groundspeak:difficulty").item(0).getTextContent());
        cache.terrain = Double.parseDouble(cacheElement.getElementsByTagName("groundspeak:terrain").item(0).getTextContent());
        cache.owner = cacheElement.getElementsByTagName("groundspeak:owner").item(0).getTextContent();
        cache.placedBy = cacheElement.getElementsByTagName("groundspeak:placed_by").item(0).getTextContent();
        cache.country = cacheElement.getElementsByTagName("groundspeak:country").item(0).getTextContent();
        cache.state = cacheElement.getElementsByTagName("groundspeak:state").item(0).getTextContent();
        cache.dayValue = cache.logDateTime.getDayOfMonth();
        cache.monthValue = cache.logDateTime.getMonthValue();

        var placedOnParts = rootElement.getElementsByTagName("time").item(0).getTextContent().split("T");
        cache.placedOn = isProjectGC ? LocalDate.parse(placedOnParts[0]) : convertGroundspeakDate(placedOnParts[0]);
        cache.placedOnMonth = cache.placedOn.getMonthValue();
        cache.placedOnDay = cache.placedOn.getDayOfMonth();
        cache.placedOnYear = cache.placedOn.getYear();

        cache.placedOnDayOfWeek = cache.placedOn.getDayOfWeek().getValue();
        cache.dayOfWeekValue = cache.logDateTime.getDayOfWeek().getValue();
        cache.ftf = getFtfFromLog(cache.logText);

        cache.archived = "True".equalsIgnoreCase(cacheElement.getAttribute("archived"));
        cache.disabled = "False".equalsIgnoreCase(cacheElement.getAttribute("available"));

        var attributes = cacheElement.getElementsByTagName("groundspeak:attributes").item(0).getChildNodes();
        for (int i = 0; i < attributes.getLength(); i++) {
            var node = attributes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                var attributeId = Integer.valueOf(node.getAttributes().getNamedItem("id").getTextContent());
                var attributeInc = Integer.valueOf(node.getAttributes().getNamedItem("inc").getTextContent());
                var attr = Attribute.of(attributeId);
                cache.addAttribute(1 == attributeInc ? attr : attr.getOpposite());
            }
        }

        geocaches.add(cache);
    }

    private Boolean getFtfFromLog(String logText) {
        if (null == logText) {
            return false;
        }

        String logLower = logText.toLowerCase();
        return logLower.contains("{ftf}") || logLower.contains("[ftf]") || logLower.contains("(ftf)");
    }

    private LocalDate convertGroundspeakDate(String dateText) {
        return LocalDate.parse(dateText);
    }
}
