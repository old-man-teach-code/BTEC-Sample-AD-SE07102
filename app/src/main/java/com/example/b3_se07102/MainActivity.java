package com.example.b3_se07102;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd; // Khai báo Button để liên kết với button trong layout
    private ListView lvTasks; // Khai báo ListView để liên kết với ListView trong layout
    private ArrayList<String> tasksList; // Khai báo ArrayList để lưu trữ danh sách các task
    private ArrayAdapter<String> tasksAdapter; // Khai báo ArrayAdapter để liên kết với ListView trong layout

    private static final int ADD_TASK_REQUEST_CODE = 1; // Nhận biết yêu cầu thêm task
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Khởi tạo instance của Activity !!!
        EdgeToEdge.enable(this); // Khởi tạo EdgeToEdge cho Activity
        setContentView(R.layout.activity_main); // Gắn layout cho Activity !!!

        // Khởi tạo các biến
        btnAdd = findViewById(R.id.btnAddNew); // tham chiếu đến button trong layout bằng id (btnAddNew)
        lvTasks = findViewById(R.id.lvTasks); // tham chiếu đến ListView trong layout bằng id (lvTasks)

        // Khởi tạo ArrayList và ArrayAdapter
        tasksList = new ArrayList<>();
        // new ArrayAdapter - Khởi tạo giá trị rỗng
        // this - Context của Activity hiện tại (MainActivity)
        // android.R.layout.simple_list_item_1 - Layout mặc định cho mỗi item trong ListView
        // tasksList - Danh sách các task (ArrayList - đã được khai báo bên trên)
        tasksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasksList);
        // Gắn ArrayAdapter vào ListView
        lvTasks.setAdapter(tasksAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() { // cấu trúc tạo sự kiện click
            @Override // ghi đè phương thức onClick (cấu trúc sự kiện click)
            public void onClick(View v) { // phương thức onClick (cấu trúc sự kiện click)
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                // Khởi tạo Intent để chuyển sang AddTaskActivity
                // MainActivity.this - Context của Activity hiện tại (MainActivity)
                // AddTaskActivity - Activity đích (AddTaskActivity)
                // startActivity(intent); - Khởi động Activity đích (AddTaskActivity)
                startActivityForResult(intent, ADD_TASK_REQUEST_CODE);
            }
        });
        // Đặt sự kiện click cho ListView khi nhấn vào một item
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           // Các giá trị tham số:
           // parent - ListView chứa item được nhấn
           // view - View của item được nhấn
           // position - Vị trí của item trong danh sách
           // id - Id của item trong danh sách
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String selectedTask = tasksList.get(position); // Lấy item được chọn từ từ listview TaskList theo vị trí position
               Toast.makeText(MainActivity.this, "Selected: " + selectedTask, Toast.LENGTH_SHORT).show();
           }
       });

        tasksList.add("Đánh răng");
        tasksList.add("Đi chợ");
        tasksList.add("Nấu cơm");
        tasksList.add("Đi tắm");
    }

    @Override
    // Ghi đè phương thức onActivityResult để nhận kết quả trả về từ AddTaskActivity
    // onActivityResult(int requestCode, int resultCode, Intent data) -
    // Là một phương thức được gọi khi có kết quả trả về từ một Activity khác.
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        // Kiểm tra requestCode và resultCode
        super.onActivityResult(requestCode, resultCode, data);
        // Kiểm tra requestCode và resultCode để xác định đúng request và kết quả trả về có hợp lệ hay không
        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            // Lấy dữ liệu trả về từ data gửi từ AddTaskActivity
            // Giá trị này được lưu trữ với name là "task"
            String newTask = data.getStringExtra("task");
            if (newTask != null && !newTask.isEmpty()){ // Kiểm tra newTask có rỗng hay không
                tasksList.add(newTask); // Thêm newTask vào tasksList để hiển thị trong ListView
                tasksAdapter.notifyDataSetChanged(); // Thông báo cho ArrayAdapter để cập nhật dữ liệu trong ListView
            }
        }
    }

}