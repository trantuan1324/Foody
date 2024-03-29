package com.rabbyte.foody.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.FirebaseDatabase;
import com.rabbyte.foody.R;
import com.rabbyte.foody.databinding.ActivityBaseBinding;

public class BaseActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);
        mDatabase = FirebaseDatabase.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // lấy ra đối tượng cửa sổ hiện tại của ứng dụng.
        Window window = getWindow();
        window.setFlags(
            // gỡ bỏ mọi giới hạn về việc layout của cửa sổ, giúp cho cửa sổ có thể vẽ tràn khắp màn hình.
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            // cờ (flag) bổ sung để xác định rằng chúng ta muốn áp dụng cấu hình này cho cả cửa sổ đầu vào và đầu ra.
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }
}