package napoleon;

public class Person {
    private String namn;
    Person(String name)    {
        namn = name;
    }
    void setName(String name) {
        namn = name;
    }
    String getName()    {
        return namn;
    }
    public static void main(String[] args) {
        Person person1 = new Person("Villiot");
        Person person2 = person1;
        System.out.println(person2.getName());
        person1.setName("Valdemar");
        System.out.println(person2.getName());
        person2.setName("Grubbel");
        System.out.println(person1.getName());
    }
}


