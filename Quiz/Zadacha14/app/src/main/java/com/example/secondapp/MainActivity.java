package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> userList = new ArrayList<>();
    UserAdapter userAdapter;
    Button addUserBtn;
    TextView itemTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recyclerViewInit() {
        Users users = new Users(MainActivity.this);
        userList = users.getUserList();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
        recyclerView.getOnFlingListener();
        /*for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Пользователь №"+i);
            user.setUserLastName("Фамилия №"+i);
            users.add(user);
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerViewInit();
    }

    private class UserHolder extends RecyclerView.ViewHolder {
        //TextView itemTextView;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            // Генерирует элементы списка, наследник RecyclerView
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            // itemView - текущий layout single_item
            itemTextView = (TextView) itemView.findViewById(R.id.itemTextView);
        }

        public void bind(String userString) {
            // метод для отображения элементов на экран
            itemTextView.setText(userString);

        }
    }

        private class UserAdapter extends RecyclerView.Adapter<UserHolder> {
            // Адаптер -для передачи элементов на recycleView содержит 3 метода
            ArrayList<User> users;
            public UserAdapter(ArrayList<User> users) {
                this.users = users;
            }


            @Override
            public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                //метод вызывается для создания каждого элемента списка
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                return new UserHolder(inflater, viewGroup);
            }

            @Override
            public void onBindViewHolder(UserHolder userHolder, int position) {
                //метод для передачи данных в элемент списка (макета)
                User user = users.get(position);
                String userString = user.getUserName() + "\n" + user.getUserLastName();
                userHolder.bind(userString);

                itemTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedItem=user.getUserName()+"\n"+user.getUserLastName()+"\n"+user.getPhone();
                        Intent(selectedItem);

                    }
                });
            }

            public void Intent (String selectedItem) {
                Intent intent = new Intent(MainActivity.this, SeeUserActivity.class);
                intent.putExtra("dataUser", selectedItem);
                startActivity(intent);
            }

            @Override
            public int getItemCount() {
                return users.size();
            }
        }
    }
