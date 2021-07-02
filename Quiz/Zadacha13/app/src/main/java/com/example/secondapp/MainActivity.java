package com.example.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<>();
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview); // получаем ссылку на компонент RecyclerView - комп., который отображает список
        // указываем как необходимо упорядочить будущее размещение списка
        // LinearLayoutManager - упорядочивает элементы в виде списка с одной колонкой
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        // создаем 100 контактов и добавляем их в коллекцию ArrayList
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Пользователь №"+i);
            user.setUserLastName("Фамилия №"+i);
            users.add(user);
        }
        userAdapter = new UserAdapter(users);
        recyclerView.setAdapter(userAdapter);
    }

    // ViewHolder - элемент списка
    private static class UserHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        // конструктор. Inflater - раздувает макет
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            // itemView - текущий layout single_item
            itemTextView = itemView.findViewById(R.id.itemTextView);
        }

        public void bind(String userString){
            itemTextView.setText(userString);
        }
    }

    // Adapter - помещает элементы списка на RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {
        ArrayList<User> users;
        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }

        // Метод для создания нового ViewHolder (элемента списка)
        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);   // раздувает макет
            return new UserHolder(inflater, viewGroup);
        }

        // Извлекает данные пользователя-контакта и передает во ViewHolder через его метод bind()
        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            userHolder.bind(userString);
        }

        // Возвращает кол-во объектов в коллекции ArrayList для размещения
        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}