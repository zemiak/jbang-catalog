import java.util.HashMap;
import java.util.Map;

enum Attribute {
    dogs(1, "Dogs"),
    fee(2, "Access or parking fee"),
    climbing_gear(3, "Climbing gear"),
    boat(4, "Boat"),
    scuba(5, "Scuba gear"),
    kids(6, "Recommended for kids"),
    onehour(7, "Takes less than an hour"),
    scenic(8, "Scenic view"),
    hiking(9, "Significant Hike"),
    climbing(10, "Difficult climbing"),
    wading(11, "May require wading"),
    swimming(12, "May require swimming"),
    available(13, "Available at all times"),
    night(14, "Recommended at night"),
    winter(15, "Available during winter"),
    poisonoak(17, "Poisonous plants"),
    dangerousanimals(18, "Dangerous Animals"),
    ticks(19, "Ticks"),
    mine(20, "Abandoned mines"),
    cliffs(21, "Cliff / falling rocks"),
    hunting(22, "Hunting"),
    danger(23, "Dangerous area"),
    wheelchair(24, "Wheelchair accessible"),
    parking(25, "Parking available"),
    publictransportation(26, "Public transportation"),
    water(27, "Drinking water nearby"),
    restrooms(28, "Public restrooms nearby"),
    phone(29, "Telephone nearby"),
    picnic(30, "Picnic tables nearby"),
    camping(31, "Camping available"),
    bicycles(32, "Bicycles"),
    motorcycles(33, "Motorcycles"),
    quads(34, "Quads"),
    jeeps(35, "Off-road vehicles"),
    snowmobiles(36, "Snowmobiles"),
    horses(37, "Horses"),
    campfires(38, "Campfires"),
    thorn(39, "Thorns"),
    stealth(40, "Stealth required"),
    stroller(41, "Stroller accessible"),
    firstaid(42, "Needs maintenance"),
    cow(43, "Watch for livestock"),
    flashlight(44, "Flashlight required"),
    landf(45, "Lost And Found Tour"),
    rv(46, "Truck Driver/RV"),
    field_puzzle(47, "Field Puzzle"),
    uv(48, "UV Light Required"),
    snowshoes(49, "Snowshoes"),
    skiis(50, "Cross Country Skis"),
    s_tool(51, "Special Tool Required"),
    nightcache(52, "Night Cache"),
    parkngrab(53, "Park and Grab"),
    abandondedbuilding(54, "Abandoned Structure"),
    hike_short(55, "Short hike (less than 1km)"),
    hike_med(56, "Medium hike (1km-10km)"),
    hike_long(57, "Long Hike (+10km)"),
    fuel(58, "Fuel Nearby"),
    food(59, "Food Nearby"),
    wirelessbeacon(60, "Wireless Beacon"),
    partnership(61, "Partnership Cache"),
    seasonals(62, "Seasonal Access"),
    touristok(63, "Tourist Friendly"),
    treeclimbing(64, "Tree Climbing"),
    frontyard(65, "Front Yard (Private Residence)"),
    teamwork(66, "Teamwork Required"),
    geotour(67, "Geotour"),
    bonuscache(69, "Bonus Cache"),
    powertrail(70, "Power Trail"),
    challengecache(71, "Challenge Cache"),
    hqsolutionchecker(72, "HQ solution checker"),

    nodogs(97, "No dogs allowed"),
    nokids(102, "Not recommended for kids"),
    noonehour(103, "Takes more than 1 hour"),
    noscenic(104, "No scenic view"),
    nohiking(105, "Not a significant hike"),
    noclimbing(106, "No difficult climbing"),
    noavailable(109, "Not available 24/7"),
    nonight(110, "Not recommended at night"),
    nowinter(111, "Not available in winter"),
    nopoisonoak(113, "No poison plants"),
    nowheelchair(120, "Not wheelchair-accessible"),
    noparking(121, "No parking available"),
    nowater(123, "No drinking water available"),
    norestrooms(124, "No public restrooms nearby"),
    nophone(125, "No telephone nearby"),
    nopicnic(126, "No picnic tables nearby"),
    nocamping(127, "No camping"),
    nobicycles(128, "No bicycles"),
    nomotorcycles(129, "No motorcycles"),
    noquads(130, "No quads"),
    nojeeps(131, "No off-road vehicles"),
    nosnowmobiles(132, "No snowmobiles"),
    nohorses(133, "No horses"),
    nocampfires(134, "No campfires"),
    nostealth(136, "No stealth required"),
    nostroller(137, "Not stroller-accessible"),
    norv(142, "Not truck-driver-/RV-accessible"),
    nofield_puzzle(143, "Not a field puzzle"),
    nonightcache(148, "Not a night cache"),
    noparkngrab(149, "Not a park-and-grab"),
    noabandondedbuilding(150, "Not in an abandoned structure"),
    nohike_short(151, "Not a short hike"),
    nohike_med(152, "Not a medium hike"),
    nohike_long(153, "Not a long hike"),
    nofuel(154, "No fuel nearby"),
    nofood(155, "No food nearby"),
    notouristok(159, "Not recommended for tourists"),
    notreeclimbing(160, "No tree-climbing required"),
    nofrontyard(161, "Not in a front yard"),
    noteamwork(162, "No teamwork required");

    private Integer id;
    private String name;

    static Map<Attribute, Attribute> opposites = new HashMap<>();

    static {
        opposites.put(dogs, nodogs);
        opposites.put(kids, nokids);
        opposites.put(onehour, noonehour);
        opposites.put(scenic, noscenic);
        opposites.put(hiking, nohiking);
        opposites.put(climbing, noclimbing);
        opposites.put(available, noavailable);
        opposites.put(night, nonight);
        opposites.put(winter, nowinter);
        opposites.put(poisonoak, nopoisonoak);
        opposites.put(wheelchair, nowheelchair);
        opposites.put(parking, noparking);
        opposites.put(water, nowater);
        opposites.put(restrooms, norestrooms);
        opposites.put(phone, nophone);
        opposites.put(picnic, nopicnic);
        opposites.put(camping, nocamping);
        opposites.put(bicycles, nobicycles);
        opposites.put(motorcycles, nomotorcycles);
        opposites.put(quads, noquads);
        opposites.put(jeeps, nojeeps);
        opposites.put(snowmobiles, nosnowmobiles);
        opposites.put(horses, nohorses);
        opposites.put(campfires, nocampfires);
        opposites.put(stealth, nostealth);
        opposites.put(stroller, nostroller);
        opposites.put(rv, norv);
        opposites.put(field_puzzle, nofield_puzzle);
        opposites.put(nightcache, nonightcache);
        opposites.put(parkngrab, noparkngrab);
        opposites.put(abandondedbuilding, noabandondedbuilding);
        opposites.put(hike_short, nohike_short);
        opposites.put(hike_med, nohike_med);
        opposites.put(hike_long, nohike_long);
        opposites.put(fuel, nofuel);
        opposites.put(food, nofood);
        opposites.put(touristok, notouristok);
        opposites.put(treeclimbing, notreeclimbing);
        opposites.put(frontyard, nofrontyard);
        opposites.put(teamwork, noteamwork);
    }

    Attribute(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Attribute getOpposite() {
        return opposites.containsKey(this) ? opposites.get(this) : null;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Attribute of(Integer id) {
        for (Attribute attribute: Attribute.values()) {
            if (attribute.getId().equals(id)) {
                return attribute;
            }
        }

        throw new RuntimeException(null == id ? "Attribute ID is null" : "Attribute ID " + id + " not found");
    }
}
