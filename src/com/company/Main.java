package com.company;

import java.sql.*;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws Exception {

        int code = Аuthorization.authenticate();
        if (code == 0) {
            System.out.println("Login success");
        } else {
            System.out.println("Login failed");
            System.exit(0);  // Выход из программы при неправильном Вводе пароля

        }



        // Подключаем базу данных
        BD_connection BD = new BD_connection();
        Connection conn =  BD.To_BD("jdbc:postgresql://localhost:5432/postgres","postgres","1");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner_string = new Scanner(System.in);
        Scanner scanner_int = new Scanner(System.in);
        Statement statement = conn.createStatement();

        // Создаем простую систему меню
        int x = 0;
        String s ="";

        while (!"4".equals(s)){
            System.out.println("1. Нажмите 1 что бы работать с доктором: Удалить/Просмотреть/Добавить 1");
            System.out.println("2. Нажмите 2 что бы работать с Пациентом: Удалить/Редактировать/Просмотреть/Добавить 2");
            System.out.println("3. Нажмите 3 что бы работать с Приемом проциетов: Удалить/Редактировать/Просмотреть/Добавить 3");
            System.out.println("4. Для выхода из приложения введите 4");
            s = scanner.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e){
                System.out.println("Неверный ввод");
            }

            switch (x){
                case 1:
                    int doc = 0;
                    String m ="";
                    while (!"4".equals(m)) {
                        System.out.println("1. Добавить Доктора в базу Данных 1");
                        System.out.println("2. Удалить доктора из базы Данных 2");
                        System.out.println("3. Просмотреть всех докторов 3");
                        System.out.println("4. Для выхода из приложения введите 4");
                        m = scanner.next();

                        try {
                            doc = Integer.parseInt(m);
                        } catch (NumberFormatException e){
                            System.out.println("Неверный ввод");
                        }
                        switch (doc) {
                            case 1:

                                System.out.println("1. Введите ФИО доктора :");
                                var name = scanner_string.nextLine();

                                System.out.println("1. Введите Специализацию доктора :");
                                var spec = scanner_string.nextLine();

                                String  SQL_INSERT_DOCTOR = "insert into doctor (\"FIO\",\"specialization\") Values (?, ?)";

                                PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_DOCTOR);

                                preparedStatement.setString(1, name);
                                preparedStatement.setString(2, spec);

                                int rows = preparedStatement.executeUpdate();
                                System.out.printf("%d rows added  : \n", rows);

                                break;
                            case 2:

                                System.out.println("1. Введите id соотв. доктора :");
                                var id = scanner_string.nextInt();

                                String  SQL_DELETE_DOCTOR = "delete from doctor where id = ?";

                                PreparedStatement preparedStatement1 = conn.prepareStatement(SQL_DELETE_DOCTOR);

                                preparedStatement1.setInt(1, id);

                                int rows_up = preparedStatement1.executeUpdate();

                                System.out.printf("%d rows delete  : \n", rows_up);
                                break;
                            case 3:
                                String  SQL_SELECT_TASKS = "select * from doctor";
                                ResultSet result1 = statement.executeQuery(SQL_SELECT_TASKS);
                                System.out.println("Id | " + "FIO          | " + " specialization");
                                while (result1.next()) {
                                    System.out.println(result1.getInt("id") + " | " + result1.getString("FIO") + " | " + result1.getString("specialization"));
                                }
                                break;
                            case 4:
                                // вызов метода 2
                                break;
                        }
                        }
                    break;
                case 2:
                    int doc_1 = 0;
                    String m_1 ="";
                    while (!"5".equals(m_1)) {
                        System.out.println("1. Добавить пациета в базу Данных 1");
                        System.out.println("2. Удалить пациета из базы Данных 2");
                        System.out.println("3. Просмотреть всех пациетов 3");
                        System.out.println("4. Редактировать данные пациента 4.");
                        System.out.println("5. Для выхода из приложения введите 5");
                        m_1 = scanner.next();

                        try {
                            doc_1 = Integer.parseInt(m_1);
                        } catch (NumberFormatException e){
                            System.out.println("Неверный ввод");
                        }
                        switch (doc_1) {
                            case 1:
                                // вызов метода 2
                                System.out.println("1. Введите ФИО пациента :");
                                var name = scanner_string.nextLine();

                                System.out.println("1. Введите возраст пациента :");
                                var age = scanner_int.nextInt();
                                scanner_int.nextLine();

                                System.out.println("1. Введите диагноз :");
                                var diag = scanner_string.nextLine();

                                System.out.println("1. Введите дату регитрации :");
                                var date = scanner_string.nextLine();

                                String  SQL_INSERT_PAT = "insert into patient (\"FIO\",\"age\",\"diagnosis\",\"regist\") Values (?, ?, ?, ?)";

                                PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_PAT);

                                preparedStatement.setString(1, name);
                                preparedStatement.setInt(2, age);
                                preparedStatement.setString(3, diag);
                                preparedStatement.setDate(4, Date.valueOf(date));

                                int rows = preparedStatement.executeUpdate();
                                System.out.printf("%d rows added  : \n", rows);
                                break;
                            case 2:
                                System.out.println("1. Введите id соотв. пациента :");
                                var id = scanner_string.nextInt();
                                String  SQL_DELETE_PAT = "delete from patient where id = ?";
                                PreparedStatement preparedStatement1 = conn.prepareStatement(SQL_DELETE_PAT);
                                preparedStatement1.setInt(1, id);
                                int rows_up = preparedStatement1.executeUpdate();
                                System.out.printf("%d rows delete  : \n", rows_up);
                                break;
                            case 3:
                                String  SQL_SELECT_PAT = "select * from patient";
                                ResultSet result1 = statement.executeQuery(SQL_SELECT_PAT);
                                System.out.println("Id | " + "FIO          | " + " age" + "   |   DIAGNOSIS" + " |  date");
                                while (result1.next()) {
                                    System.out.println(result1.getInt("id") + " | " + result1.getString("FIO") + " | " + result1.getInt("age") + " | " + result1.getString("diagnosis") + " | " + result1.getString("regist"));
                                }
                                break;
                            case 4:

                                System.out.println("1. Введите новое значение ФИО пациента :");
                                var name_new = scanner_string.nextLine();

                                System.out.println("1. Введите новый возраст :");
                                var age_new = scanner_int.nextInt();
                                scanner_int.nextLine();
                                System.out.println("1. Введите новый диагноз :");
                                var diag_new = scanner_string.nextLine();

                                System.out.println("1. Введите новую дату регистрации формата: yyyy-mm-dd :");
                                var date_new = scanner_string.nextLine();
                                System.out.println("1. Введите id соотв. пациента :");
                                var id_new = scanner_string.nextInt();

                                String  SQL_UPDATE_PAT = "update patient set \"FIO\" = ?, \"age\" = ?, \"diagnosis\" = ?, \"regist\" = ? where id = ?";

                                PreparedStatement preparedStatement2 = conn.prepareStatement(SQL_UPDATE_PAT);

                                preparedStatement2.setString(1, name_new);
                                preparedStatement2.setInt(2, age_new);
                                preparedStatement2.setString(3, diag_new);
                                preparedStatement2.setDate(4, Date.valueOf(date_new));
                                preparedStatement2.setInt(5, id_new);

                                int rows_up_pat = preparedStatement2.executeUpdate();

                                System.out.printf("%d rows update  : \n", rows_up_pat);

                                break;
                            case 5:
                                System.exit(0);
                                break;
                        }
                    }

                    // вызов метода 2
                    break;
                case 3:
                    Scanner menu_4 = new Scanner(System.in);
                    int doc_2 = 0;
                    String m_2 ="";
                    while (!"4".equals(m_2)) {
                        System.out.println("1. Добавить Прием в базу Данных 1");
                        System.out.println("2. Просмотреть все приемы 2");
                        System.out.println("3. посмотреть приемы пациента 3");
                        System.out.println("4. Изменить статус приема 4.");
                        System.out.println("5. Для выхода из приложения введите 5");
                        m_2 = scanner.next();

                        try {
                            doc_2 = Integer.parseInt(m_2);
                        } catch (NumberFormatException e){
                            System.out.println("Неверный ввод");
                        }
                        switch (doc_2) {
                            case 1:
                                System.out.println("1. Введите id врача :");
                                var id_doctor = scanner_int.nextInt();
                                scanner_int.nextLine();

                                System.out.println("1. Введите id пациента :");
                                var id_patient = scanner_int.nextInt();
                                scanner_int.nextLine();

                                System.out.println("1. Введите дату :");
                                var data_ap = scanner_string.nextLine();

                                System.out.println("1. Введите статус (завершен или нет) ( F or T ) :");
                                var status = scanner_string.nextLine();

                                System.out.println("1. Введите время в формате 00:00:00 :");
                                var time_ap = scanner_string.nextLine();
                                Time.valueOf(time_ap);
                                System.out.println(Time.valueOf(time_ap));

                                String  SQL_INSERT_AP = "insert into appointment (\"id_doctor\",\"id_patient\",\"date\",\"status\",\"times\") Values (?, ?, ?, ?, ?)";

                                PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_AP);

                                preparedStatement.setInt(1, id_doctor);
                                preparedStatement.setInt(2, id_patient);
                                preparedStatement.setDate(3, Date.valueOf(data_ap));
                                preparedStatement.setBoolean(4, Boolean.valueOf(status));
                                preparedStatement.setTime(5, Time.valueOf(time_ap));

                                int rows = preparedStatement.executeUpdate();
                                System.out.printf("%d rows added  : \n", rows);

                                break;
                            case 2:

                                String  SQL_SELECT_AP = "select * from appointment";
                                ResultSet result1 = statement.executeQuery(SQL_SELECT_AP);
                                System.out.println("Id | " + "id_doctor         | " + " id_patient" + "   |   Date" + " |  status" + "  |  Times");
                                while (result1.next()) {
                                    System.out.println(  " | " + result1.getInt("id") +  " | " + result1.getInt("id_doctor") + " | " + result1.getInt("id_patient") + " | " + result1.getString("date") + " | " + result1.getString("status") + " | " + result1.getString("times"));
                                }

                                break;
                            case 3:
                                System.out.println("1. Введите id соотв. пациента :");
                                var id_new = scanner_string.nextInt();
                                String.valueOf(id_new);
                                String  SQL_UPDATE_PAT = "select * from appointment where id =" + id_new;
                                ResultSet result = statement.executeQuery(SQL_UPDATE_PAT);
                                System.out.println("Id | " + "id_doctor         | " + " id_patient" + "   |   Date" + " |  status" + "  |  Times");
                                while (result.next()) {
                                    System.out.println(  " | " + result.getInt("id") +  " | " + result.getInt("id_doctor") + " | " + result.getInt("id_patient") + " | " + result.getString("date") + " | " + result.getString("status") + " | " + result.getString("times"));
                                }
                                break;
                            case 4:

                                System.out.println("1. Введите новый статус:");
                                var new_status = scanner_string.nextLine();
                                System.out.println("1. Введите id соотв. пациента :");
                                var id_r = scanner_int.nextInt();
                                scanner_int.nextLine();

                                String  SQL_UPDATE_AP = "update appointment set \"status\" = ?  where id = ?";

                                PreparedStatement preparedStatement3 = conn.prepareStatement(SQL_UPDATE_AP);

                                preparedStatement3.setBoolean(1, Boolean.valueOf(new_status));
                                preparedStatement3.setInt(2, id_r);

                                int rows_up_pat = preparedStatement3.executeUpdate();

                                System.out.printf("%d rows update  : \n", rows_up_pat);

                                break;
                            case 5:
                                System.exit(0);
                                break;

                        }
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;

            }
        }
        System.out.println("До свидания!");

    }
}
