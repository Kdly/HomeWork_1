public class Main {
    public static void main(String[] args) {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.put(1, "один");
        map.put(2, "два");
        System.out.println(map.get(1));

        map.put(1, "новое значение");
        System.out.println(map.get(1));

        map.remove(2);
        System.out.println(map.get(2));
        System.out.println("Размер " + map.size());

        CustomHashMap<String, Integer> map2 = new CustomHashMap<>();
        map2.put("один", 1);
        map2.put("два", 2);
        System.out.println(map2.get("два"));

        map2.put("два", 20);
        System.out.println(map2.get("два"));

        map2.remove("один");
        System.out.println(map2.get("один"));
        System.out.println("Размер: " + map2.size());
    }
}
