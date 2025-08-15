package letnecesty2025;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LogHandler {
    public LocalDateTime date;
    public String text;
    public String cacherName = null;

    public LogHandler(Element cache, Boolean isProjectGC) {
        NodeList logs = cache.getElementsByTagName("groundspeak:logs").item(0).getChildNodes();
        String finder;
        Element log;
        Node node;
        String logType;

        for (int i = 0; i < logs.getLength(); i++) {
            node = logs.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                log = (Element) node;
                finder = log.getElementsByTagName("groundspeak:finder").item(0).getTextContent().toLowerCase();
                logType = log.getElementsByTagName("groundspeak:type").item(0).getTextContent().toLowerCase();

                if (null == cacherName) {
                    cacherName = finder;
                }

                if (foundIt(logType)) {
                    this.extractData(log, isProjectGC);
                    return;
                }
            }
        }

        throw new IllegalStateException("Cannot find any log in cache " + cache.getElementsByTagName("groundspeak:name").item(0).getTextContent());
    }

    private void extractData(Element log, Boolean isProjectGC) {
        var dateText = log.getElementsByTagName("groundspeak:date").item(0).getTextContent().replace("Z", "");
        this.date = isProjectGC ? LocalDateTime.parse(dateText) : convertGroundspeakDateTime(dateText);

        this.text = log.getElementsByTagName("groundspeak:text").item(0).getTextContent();
    }

    private LocalDateTime convertGroundspeakDateTime(String dateTimeText) {
        ZonedDateTime pst = LocalDateTime.parse(dateTimeText).atZone(ZoneId.of("America/Los_Angeles"));
        return pst.toLocalDateTime().plusSeconds(pst.getOffset().getTotalSeconds());
    }

    private boolean foundIt(String logType) {
        return "found it".equals(logType) || "attended".equals(logType) || "webcam photo taken".equals(logType);
    }
}
