package com.example.b3_se07102;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText edtTask;

    private TextView tvScreenName;

    private Boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        btnSubmit = findViewById(R.id.btnSubmit);
        edtTask = findViewById(R.id.edtTask);
        tvScreenName = findViewById(R.id.tvScreenName);
        int position = -1;
        Intent intent = getIntent();
        if (intent != null) {
            String task = intent.getStringExtra("task");
            position = intent.getIntExtra("position", -1);
            if (task != null && position > 0) {
                edtTask.setText(task);
                isEdit = true;
                btnSubmit.setText("Update the task");
                tvScreenName.setText("Update existed task ");
            }
        }

        int finalPosition = position;
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEdit){
                    actionAddNewTask();
                    return;
                }else {
                    actionEditTask(finalPosition);
                    return;
                }
            }
        });
    }

    private void actionEditTask(final int position){
        String task = edtTask.getText().toString().trim();
        if (task.isEmpty()) {
            Toast.makeText(AddActivity.this, "Vui lòng nhập nhiệm vụ", Toast.LENGTH_SHORT).show();
        }else{
            Intent resultIntent = new Intent(); // Tạo một Intent để trả về kết quả
            resultIntent.putExtra("task", task); // Truyền dữ liệu vào trong Intent
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent); // set đây là kết quả trả về của Activity trước đó với mã trả về là RESULT_OK
            finish(); // Kết thúc Activity hiện tại
        }
    }

    private void actionAddNewTask(){
        String task = edtTask.getText().toString().trim();
        if (task.isEmpty()) {
            // Hiển thị thông báo alert
            // Toast - thông báo ngắn được định nghĩa sẵn
            // AddActivity.this - Activity hiện tại
            // "Vui lòng nhập nhiệm vụ" - Nội dung thông báo
            // Toast.LENGTH_SHORT - Thời gian hiển thị thông báo
            // show() - Hiển thị thông báo
            Toast.makeText(AddActivity.this, "Vui lòng nhập nhiệm vụ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddActivity.this, "Thêm nhiệm vụ thành công: " + task, Toast.LENGTH_LONG).show();
            Intent resultIntent = new Intent(); // Tạo một Intent để trả về kết quả
            resultIntent.putExtra("task", task); // Truyền dữ liệu vào trong Intent
            setResult(RESULT_OK, resultIntent); // set đây là kết quả trả về của Activity trước đó với mã trả về là RESULT_OK
            finish(); // Kết thúc Activity hiện tại
        }
    }
}