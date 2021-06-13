public class Hotel {
    Room[] rooms;

    public Hotel(Room[] rooms) {
        this.rooms = rooms;
    }

    public void getFreeRooms() {
        String freeRoomsList = "";
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].isFree()) freeRoomsList += rooms[i].getRoomNumber() + ", ";
        }
        System.out.println("Номера свободных комнат " + freeRoomsList);
    }

    public void reserveRoom(byte roomNumber) {
        String info = "Такой комнаты не существует";
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (room.getRoomNumber() == roomNumber && room.isFree()) {
                room.setFree(false);
                info = ("Комната номер " + roomNumber + " успешно забронированна");
                break;
            } else if (room.getRoomNumber() == roomNumber && !room.isFree()) {
                info = "Комната " + roomNumber + " занята";
                break;
            }
        }
        System.out.println(info);
    }

    public void freeRoom(byte roomToFreeNumber) {
        String info = "Такой комнаты не существует";
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (room.getRoomNumber() == roomToFreeNumber && !room.isFree()) {
                room.setFree(true);
                info = ("Комната номер " + roomToFreeNumber + " теперь доступна для бронирования.");
                break;
            } else if (room.getRoomNumber() == roomToFreeNumber && room.isFree()) {
                info = "Комната " + roomToFreeNumber + " уже и так свободна";
                break;
            }

        }
        System.out.println(info);
    }


    public void showRooms(String options) {

        StringBuilder RoomsList = new StringBuilder();
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (options.equals("Wifi")) {
                if (room.isWifi())
                    RoomsList.append(rooms[i].getRoomNumber()).append(", ");
            } else if (options.equals("WC")) {
                if (room.isWc())
                    RoomsList.append(rooms[i].getRoomNumber()).append(", ");
            } else if (options.equals("едой")) {
                if (room.isEat())
                    RoomsList.append(rooms[i].getRoomNumber()).append(", ");
            }
        }
        System.out.println("Номера комнат с " + options + ": " + RoomsList.toString());
    }


    public void showRooms(byte options) {
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (room.getRoomNumber() == options) {
                System.out.println(room.toString());
                break;
            }
        }
    }

    public void showRoomsBeds(byte beds) {
        StringBuilder RoomsList = new StringBuilder();
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (room.getQuantity() == beds)
                RoomsList.append(room.getRoomNumber()).append(", ");

        }
        System.out.println("Количество комнат с числом спальных мест " + beds + "\n" + RoomsList.toString());
    }

}