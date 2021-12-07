package test.streamAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainAnimal {
    private static List<Animal> getAnimal() {
        return List.of(
                new Animal("Слон", 20, Classification.HERBIVORE),
                new Animal("Лев", 10, Classification.PREDATOR),
                new Animal("Гиена", 11, Classification.PREDATOR),
                new Animal("Жираф", 7, Classification.HERBIVORE),
                new Animal("Гибон", 35, Classification.OMNIVOROUS),
                new Animal("Лошадь", 36, Classification.HERBIVORE),
                new Animal("Рысь", 2, Classification.PREDATOR),
                new Animal("Динозавр", 200, Classification.PREDATOR)
        );
    }

    public static void main(String[] args) {
        List<Animal> animals = getAnimal();
        //**************************************************************************************************************
        //                                          Old (Императивный подход)
        //**************************************************************************************************************

//        List<Animal> predators = new ArrayList<>();
//
//        for (Animal animal : animals) {
//            if (animal.getClassification().equals(Classification.PREDATOR)) {
//                predators.add(animal);
//            }
//        }
//        predators.forEach(System.out::println);

        //**************************************************************************************************************
        //                                            New (Декларативный)
        //**************************************************************************************************************

        /*                          /Filter/                Stream API               \Group\
                                        /Sort/                                 \Min\
                                            /All match/                   \Max\
                                                    /Any match/ | \None match\
         */

        //                                *******************Filter*******************
        List<Animal> list = animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.OMNIVOROUS))
                .collect(Collectors.toList());
        //list.forEach(System.out::println);
        ////                                *******************Sort*******************
        list = animals.stream()
                        .sorted(Comparator.comparing(Animal::getAge)
                                .thenComparing(Animal::getClassification) // Если есть одинаковые возрасты,
                                                                          // то это (.thenComparing) будет работать
                                                                          // как доп. сортировка по классификации
                                .reversed()) // Реверс .reversed()
                        .collect(Collectors.toList());
        //list.forEach(System.out::println);

        //                                *******************All match****************
        // Все элементы удовлетворяют условию ".allMatch()"
        boolean allMatch = animals.stream()
                .allMatch(animal -> animal.getAge() > 10);
        //System.out.println(allMatch);

        //                                *******************Any match****************
        // Если хотя бы 1 элемент удовлетворяет условию
        boolean anyMatch = animals.stream()
                .anyMatch(animal -> animal.getAge() > 10);
        //System.out.println(anyMatch);

        //                                *******************None match***************
        // Если нет элемента то true
        boolean noneMatch = animals.stream()
                .noneMatch(animal -> animal.getName().equals("Медведь"));
        //System.out.println(noneMatch);

        //                                *******************Max or min***************
        // Используется Optional (.ifPresent), для того что бы не вылетела NullPointerException
//        animals.stream()
//                .min(Comparator.comparing(Animal::getAge))
//                .ifPresent(System.out::println);

        //                                *******************Group********************
        Map<Classification, List<Animal>> classificationListMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getClassification));

//        classificationListMap.forEach((classification, animals1) -> {
//            System.out.println(classification);
//            animals1.forEach(System.out::println);
//            System.out.println();
//        });

        //                                *******************All**********************
        //Самый старый хищный
        Optional<String> optional = animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.PREDATOR))
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getName);
        optional.ifPresent(System.out::println);
    }
}
