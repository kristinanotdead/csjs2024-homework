package ru.croc.javaschool2024.soloveva.task15;
import ru.croc.javaschool2024.soloveva.task15.db.DbProcessor;
import ru.croc.javaschool2024.soloveva.task15.models.Client;
import ru.croc.javaschool2024.soloveva.task15.models.Pet;
import ru.croc.javaschool2024.soloveva.task15.readers.DataReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String... args) throws Exception {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("Не установлен драйвер H2!");
            return;
        }

        if (args.length != 1){
            System.out.println("Неверное количество аргументов!");
            return;
        }

        DbProcessor processor = new DbProcessor("jdbc:h2:tcp://localhost/~/test", "sa", "");
        Map<Client, Set<Pet>> clientsMap;

        try {
            clientsMap = DataReader.readFromFile(args[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Непрвильный формат данных!");
            return;
        }

        try {
            processor.deleteDb();
            processor.createDb();
            processor.importData(clientsMap);

            System.out.println("Данные импортированы успешно!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
