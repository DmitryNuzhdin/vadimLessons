package tasks.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ValidParentheses {
    public static boolean checkValidness(String combination) {
        // довольно классическая задача
        // написать функцию, проверяющую правильность комбинации скобок
        // на вход подается комбинация из обычных, квадратных или фигурных скобок например "[()[{}]]"
        // нужно проверить - является ли такая комбинация верной:
        // 1) каждой открывающей скобке должна соответсвовать закрывающая скобка
        // 2) каждая пара скобок должна начинаться с отрывающей
        // 3) если пара скобок открывается внутри другой пары, то и закрываться она должна внутри нее
        // валидные пары ([{}]), (()[{()}([])])
        // не валидные {], ({[}])
        // нужно попытаться написать код так, чтобы можно было легко добавить новые типы скобок, например <>
        // возможно стоит создать и применить какие-то дополнительные классы
        // чтобы понять какие - нужно ответить на вопрос, какую функцию они выполняют? в чем их смысл

        // подсказка: использовать стэк https://ru.wikipedia.org/wiki/%D0%A1%D1%82%D0%B5%D0%BA
        // в java он реализован в LinkedList (методы push() и pop() )

        // (*) Сделать так, чтобы добавить новый вид скобок можно было максимально просто и понятно.

        List<ParenthesisPair> types = new ArrayList<>(); //список пар скобок которые знает программа
        types.add(new ParenthesisPair('(', ')'));
        types.add(new ParenthesisPair('[', ']'));
        types.add(new ParenthesisPair('{', '}'));
        //можно легко в одну строчку добавить новый тип

        LinkedList<Character> chars = new LinkedList<>();
        for (char c:combination.toCharArray()) {
            chars.add(c);
        }
        if (chars.size() == 0) return true;
        while (deletePairs(chars, types)) {
            if (chars.size() == 0) return true;
        }  // удаляем пары до тех пор пока они удаляются

        return false;
    }

    private static boolean deletePairs(LinkedList<Character> list, List<ParenthesisPair> types) {
        //метод удаляет пары, и возвращает true если что-то было удалено
        boolean answer = false;  //изначально ответ отрицательный
        for (int i=0; i < list.size() - 1; i++) {
            ParenthesisPair pair = new ParenthesisPair(list.get(i), list.get(i + 1));  //собираем пару из скобок с индексами i и i + 1
            // на самом деле здесь есть проблема - list.get() в LinkedList выполняется за O(n)
            // ArrayList в свою очередь имеет O(n) для .remove(), что тоже плохо
            // поэтому такое решение будет супер медленное наверное в итоге получится вообще O(n^3) что очень плохо
            // но сейчас речь о другом, в идеале алгоритм должен быть немного другим в принципе
            for (ParenthesisPair type: types){
                if (type.equals(pair)) {  //если здесь сравнивать через == , то работать не будет (попробуй понять почему)
                    list.remove(i);
                    list.remove(i);
                    answer = true;   //отмечаем что было удаление
                }
            }
        }
        return answer;
    }
}

class ParenthesisPair {
    // простой класс - контейнр 2 величин с реализованным (сгенеренным автоматически через alt-insert) методом equals
    // это "тип данных" - пара скобок, благодаря ему можно сравнивать сразу пары, уменьшить количество математики в коде типа
    // (listA.get(i) == listB.get(k) && listA.get(i+1) == listB.get(k+1))
    // превращается в
    // (pairA.equals(pairB))
    // существенно улучшает читаемость кода, единственный минус - создание этого класса - на Java он громоздкий
    // к сожалению Java всегда останется "многословной", но вот ее наследники такие как Scala или Kotlin эту проблему решают специальными конструкциями
    // например аналогичный класс на Scala, с тем-же функционалом (даже немного больше) реализуется в 1 строчку
    // case class ParenthesisPair(left: char, right:char)
    final char left;
    final char right;

    public ParenthesisPair(char left, char right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParenthesisPair that = (ParenthesisPair) o;
        return left == that.left &&
                right == that.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}