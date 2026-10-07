package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private TextView tvUname, tvGender, tvAge, tvPassword, tvBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivAvatar = findViewById(R.id.ivDetailAvatar);
        tvUname = findViewById(R.id.tvDetailUname);
        tvGender = findViewById(R.id.tvDetailGender);
        tvAge = findViewById(R.id.tvDetailAge);
        tvPassword = findViewById(R.id.tvDetailPassword);
        tvBio = findViewById(R.id.tvDetailBio);

        // Nhận đối tượng User truyền từ Adapter/Activity
        User user = (User) getIntent().getSerializableExtra("user_data");

        if (user != null) {
            tvUname.setText(user.getUname());
            tvGender.setText("Giới tính: " + user.getGender());
            tvAge.setText("Tuổi: " + user.getAge());
            tvPassword.setText("Mật khẩu: " + user.getPassword());
            tvBio.setText(user.getShortBio());

            // Nạp ảnh đại diện qua Glide
            Glide.with(this)
                    .load(user.getUrlProfile())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_delete)
                    .into(ivAvatar);
        }
    }
}