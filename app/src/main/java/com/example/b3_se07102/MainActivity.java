package com.example.b3_se07102;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd; // Khai báo Button để liên kết với button trong layout
    private RecyclerView rvTasks; // Khai báo RecyclerView để liên kết với RecyclerView trong layout
    private ArrayList<String> tasksList; // Khai báo ArrayList để lưu trữ danh sách các task
    private TaskAdapter tasksAdapter; // Khai báo TaskAdapter để liên kết với ArrayAdapter

    private Button btnLogout;
    private static final int ADD_TASK_REQUEST_CODE = 1; // Nhận biết yêu cầu thêm task
    private static final int EDIT_TASK_REQUEST_CODE = 2; // Nhận biết yêu cầu sửa task

    SessionManager sessionManager;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Khởi tạo instance của Activity !!!
        EdgeToEdge.enable(this); // Khởi tạo EdgeToEdge cho Activity
        setContentView(R.layout.activity_main); // Gắn layout cho Activity !!!

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
//        if (!sessionManager.isLogin()){ // kiểm tra trạng thái đăng nhập (loginState) - AppData là static class
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }

        Toast.makeText(this, "Xin chao " + AppData.fullname, Toast.LENGTH_SHORT).show();

        // Khởi tạo các biến
        btnAdd = findViewById(R.id.btnAddNew); // tham chiếu đến button trong layout bằng id (btnAddNew)
        rvTasks = findViewById(R.id.rvTasks); // tham chiếu đến RecyclerView trong layout bằng id (rvTasks)
        btnLogout = findViewById(R.id.btnLogout);
        // Khởi tạo ArrayList và ArrayAdapter
        tasksList = new ArrayList<>();
        tasksAdapter = new TaskAdapter(tasksList);
        // Gắn ArrayAdapter vào ListView
        rvTasks.setAdapter(tasksAdapter);

        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        btnLogout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     sessionManager.logout();
                 }
             });

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

        tasksAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String selectedTask) {
                new AlertDialog.Builder(
                        MainActivity.this
                )
                        .setTitle("Lựa chọn hành động")
                        .setMessage("Bạn muốn xóa hay sửa " + selectedTask + " ?")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showDeleteConfirmationDialog(position);
                            }
                        })
                        .setNegativeButton("Sửa", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                                intent.putExtra("task", selectedTask);
                                intent.putExtra("position", position);
                                startActivityForResult(intent, EDIT_TASK_REQUEST_CODE);
                            }
                        }).show();
            }
        });

        tasksList.add("Đánh răng");
        tasksList.add("Đi chợ");
        tasksList.add("Nấu cơm");
        tasksList.add("Đi tắm");
        tasksList.add("Đánh răng");
        tasksList.add("Đi chợ");
        tasksList.add("Nấu cơm");
        tasksList.add("Đi tắm");
        tasksList.add("Đánh răng");
        tasksList.add("Đi chợ");
        tasksList.add("Nấu cơm");
        tasksList.add("Đi tắm");
        tasksList.add("Đánh răng");
        tasksList.add("Đi chợ");
        tasksList.add("Nấu cơm");
        tasksList.add("Đi tắm");
        tasksList.add("Đánh răng");
        tasksList.add("Đi chợ");
        tasksList.add("Nấu cơm");
        tasksList.add("Đi tắm");

        tasksAdapter.notifyDataSetChanged();
    }

    // Hàm tự khai báo: Hiển thị hộp thoại xác nhận xóa khi nhấn vào item trong ListView
    private void showDeleteConfirmationDialog(final int position) { // Truyền vào vị trí của item trong ListView
        final String selectedTask = tasksList.get(position); // Thấy nội dung của item được chọn
        new AlertDialog.Builder(this) // Khởi tạo hộp thoại xác nhận xóa bằng AlertDialog.Builder
                .setTitle("Xác nhận xóa") // Đặt tiêu đề cho hộp thoại
                .setMessage("Bạn có chắc chắn muốn xóa " + selectedTask + "?") // Đặt nội dung cho hộp thoại
                .setPositiveButton("Xác nhận xóa!", new DialogInterface.OnClickListener() { // Đặt sự kiện click cho nút "Xác nhận xóa"
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // Ghi đè method onClick (default của DialogInterface)
                        tasksList.remove(position); // Xóa item khỏi tasksList (ArrayList, với method remove)
                        tasksAdapter.notifyDataSetChanged(); // Thông báo cho ArrayAdapter để cập nhật dữ liệu trong ListView
                    }
                })
                .setNegativeButton("Hủy", null) // Đặt sự kiện cho click hủy lựa chọn
                .show();
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
        if (resultCode == RESULT_OK && data != null){
            boolean isEdit = false;
            // Lấy dữ liệu trả về từ data gửi từ AddTaskActivity
            // Giá trị này được lưu trữ với name là "task"
            String task = data.getStringExtra("task");
            int position = data.getIntExtra("position", -1);
            Toast.makeText(this, "Position: " + position + " Task: " + task, Toast.LENGTH_SHORT).show();

            if (task != null && !task.isEmpty()){ // Kiểm tra newTask có rỗng hay không
                if (position == -1) // Kiểm tra position có phải là -1 hay không
                                    // Nếu là -1 thì task là một task mới (Vì task mới chưa có vị trí trong tasksList)
                {
                    tasksList.add(task); // Thêm newTask vào tasksList để hiển thị trong ListView
                    tasksAdapter.notifyDataSetChanged(); // Thông báo cho ArrayAdapter để cập nhật dữ liệu trong ListView
                } else {
                    tasksList.set(position, task); // Thay đổi nội dung của item trong tasksList
                    tasksAdapter.notifyDataSetChanged(); // Thông báo cho ArrayAdapter để cập nhật dữ liệu trong ListView
                }

            }
        }
    }

}