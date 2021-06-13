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

    static class Hotel {
        Room[] rooms;

        public Room[] getRooms() {
            return rooms;
        }

        public Hotel(Room[] rooms) {
            this.rooms = rooms;
        }

        public void getFreeRooms(){
            String freeRoomsList = "";
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i].isFree()) freeRoomsList += rooms[i].getRoomNumber()+", ";
            }
            System.out.println("Номера свободных комнат "+freeRoomsList);
        }
        public void reserveRoom(byte roomNumber){
            String info = "Такой комнаты не существует";
            for (int i = 0; i < rooms.length; i++) {
                Room room = rooms[i];
                if(room.getRoomNumber() == roomNumber && room.isFree()){
                    room.setFree(false);
                    info = ("Комната номер "+roomNumber+" успешно забронированна");
                    break;
                }else if(room.getRoomNumber() == roomNumber && !room.isFree()){
                    info = "Комната "+roomNumber+" занята";
                    break;
                }
            }
            System.out.println(info);
        }

        public void freeRoom(byte roomNumber){
            String info = "Такой комнаты не существует";
            for (int i = 0; i < rooms.length; i++) {
                Room room = rooms[i];
                if(room.getRoomNumber() == roomNumber && !room.isFree()){
                    room.setFree(true);
                    info = ("Комната номер "+roomNumber+" успешно освобождена");
                    break;
                }else if(room.getRoomNumber() == roomNumber && room.isFree()){
                    info = "Комната "+roomNumber+" была свободна";
                    break;
                }
            }
            System.out.println(info);
        }

        public void showWiFiRooms(){

            String wifiRoomsList = "";
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i].isWifi()) wifiRoomsList += rooms[i].getRoomNumber()+", ";
            }
            System.out.println("Номера комнат c WiFi: "+ wifiRoomsList);
        }

        public void showWcRooms(){

            String wcRoomsList = "";
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i].isWc()) wcRoomsList += rooms[i].getRoomNumber()+", ";
            }
            System.out.println("Номера комнат c туалетом: "+ wcRoomsList);
        }
        public void showEatRooms(){

            String eatRoomsList = "";
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i].isEat()) eatRoomsList += rooms[i].getRoomNumber()+", ";
            }
            System.out.println("Номера комнат c питанием: "+ eatRoomsList);
        }

        public void showQuantitySortedRooms(){
            String quantitySortedRoomsList = "";
            Room[] sortedRooms = bubbleSort(rooms);

            for (int i = 0; i < rooms.length; i++) {

                quantitySortedRoomsList += rooms[i].getRoomNumber()+" мест "+ rooms[i].getQuantity() +"\n ";
            }
            System.out.println("Номера комнат c количеством спальных мест:\n "+ quantitySortedRoomsList);
        }

        public static Room[] bubbleSort(Room[] array) {
            boolean sorted = false;
            Room temp;
            while(!sorted) {
                sorted = true;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i].getQuantity() < array[i+1].getQuantity()) {
                        temp = array[i];
                        array[i] = array[i+1];
                        array[i+1] = temp;
                        sorted = false;
                    }
                }
            }
            return array;
        }
    }
}