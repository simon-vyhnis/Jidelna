package com.simon.jdelna.ui;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.simon.jdelna.R;
import com.simon.jdelna.http.model.OrderRequest;

import java.util.ArrayList;

public class PostChangesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_changes);
        ArrayList<OrderRequest> requests = (ArrayList<OrderRequest>) getIntent().getSerializableExtra("requests");
        StringBuilder builder = new StringBuilder();
        for (OrderRequest request : requests) {
            builder.append(request.getDate()).append(": ");
            if(request.getDayPart().isSelected()) {
                builder.append(request.getDayPart().getSelectedFood().getName()).append(" ").append(request.getDayPart().getSelectedFood().getContent()).append("\n");
            }else{
                builder.append("Odhlášeno\n");
            }
        }
        TextView text = findViewById(R.id.changes);
        text.setText(builder.toString());
        Button btn = findViewById(R.id.post_button);
        btn.setOnClickListener(v->{
            //POST CHANGES HERE
        });
    }
}
