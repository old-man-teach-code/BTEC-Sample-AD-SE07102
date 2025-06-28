package com.example.b3_se07102;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import okhttp3.OkHttpClient;

public class GeminiApiActivity extends AppCompatActivity {

    TextView tvAnswer;
    EditText edtQuestion;
    Button btnAsk;

    OkHttpClient client;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gemini_api);

        tvAnswer = findViewById(R.id.tvAnswer);
        edtQuestion = findViewById(R.id.edtInput);
        btnAsk = findViewById(R.id.btnAsk);

        client = new OkHttpClient();
        gson = new Gson();

        btnAsk.setOnClickListener(view -> {
            String question = edtQuestion.getText().toString();
        });
    }

    private void sendRequestGeminiAPI(String question) throws JSONException {
        String body = createRequestBody(question);
    }

    private String createRequestBody(String question) throws JSONException {
        JSONObject root = new JSONObject();
        JSONObject content = new JSONObject();
        JSONObject part = new JSONObject();
        String prompt = "Hãy trả lời câu sau đây bằng tiếng Việt và đảm bảo câu trả lời có tối đa 30 từ: " + question;
        part.put("text", prompt);
        JSONArray contents = new JSONArray();
        JSONArray parts = new JSONArray();
        parts.put(part);
        content.put("parts",parts);
        contents.put(content);
        root.put("contents", contents);
        return gson.toJson(root);
    }

}