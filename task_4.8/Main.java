import java.util.Arrays;
import java.util.Scanner;

/*
 *  ***Гостиница***
 *  1) Гостиница
 *  2) Номера (кол-во мест(1-3), сан.узел(есть/нет), питаение(есть/нет), wifi(есть/нет), свободен/занят)
 *
 *   *Задание для лабораторной работы №4*
 *   Освободить комнату
 *   Вывести свойства комнаты
 *   Показать комнаты с WiFi
 *   Показать комнаты с WC
 *   Показать комнаты с Eat
 *   Показать комнаты по кол-ву спальных мест
 *   Отобразить список команд
 * */
enum Commands {
    getFreeRooms,
    reserveRoom,
    exit,
    showCommands,
    freeRoom,
    showRoomOptions,
    showWifi,
    showWC,
    showEat,
    showRoomsBeds,
    notImplemented
}

public class Main {
    public static void main(String[] args) {
        Room[] rooms = {
                new Room((byte) 1, false, false, true, (byte) 11),
                new Room((byte) 2, true, true, false, (byte) 12),
                new Room((byte) 1, false, true, true, (byte) 13),
                new Room((byte) 3, true, false, false, (byte) 21),
                new Room((byte) 2, false, false, false, (byte) 22),
                new Room((byte) 1, true, true, true, (byte) 23),
                new Room((byte) 3, false, true, false, (byte) 31),
                new Room((byte) 3, true, true, false, (byte) 32),
                new Room((byte) 1, false, false, true, (byte) 33),
        };
        Hotel hotel = new Hotel(rooms);
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Введите команду(showCommands - отобразит список всех комманд)");
            command = scanner.nextLine();
            Commands TrueCommand;
            try {
                TrueCommand = Commands.valueOf(command);
            } catch (IllegalArgumentException e) {
                TrueCommand = Commands.notImplemented;
            }

            switch (TrueCommand) {
                case getFreeRooms:
                    hotel.getFreeRooms();
                    break;
                case reserveRoom:
                    System.out.print("Введите номер комнаты для бронирования: ");
                    byte roomNumber = (byte) scanner.nextInt();
                    scanner.nextLine();
                    hotel.reserveRoom(roomNumber);
                    break;
                case showCommands:
                    System.out.println(Arrays.toString(Commands.values()));
                    break;
                case exit:
                    return;
                case freeRoom:
                    System.out.println("Введите номер комнаты, которую необходимо освободить");
                    byte RoomToFreeNumber = (byte) scanner.nextInt();
                    scanner.nextLine();
                    hotel.freeRoom(RoomToFreeNumber);
                    break;
                case showRoomOptions:
                    System.out.println("Введите номер комнаты, которую хотите посмотреть");
                    byte RoomToShow = (byte) scanner.nextInt();
                    scanner.nextLine();
                    hotel.showRooms(RoomToShow);
                    break;
                case showWifi:
                    hotel.showRooms("Wifi");
                    break;
                case showWC:
                    hotel.showRooms("WC");
                    break;
                case showEat:
                    hotel.showRooms("едой");
                    break;
                case showRoomsBeds:
                    System.out.println("Введите количество спальных мест(1-3)");
                    byte beds = (byte) scanner.nextInt();
                    scanner.nextLine();
                    hotel.showRoomsBeds(beds);
                    break;
                default:
                    System.out.println("Неизвестная команда");
            }


        }
    }

}