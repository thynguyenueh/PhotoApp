package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetailAvatar;
    private TextView tvDetailUname, tvDetailBio, tvDetailPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivDetailAvatar = findViewById(R.id.ivDetailAvatar);
        tvDetailUname = findViewById(R.id.tvDetailUname);
        tvDetailBio = findViewById(R.id.tvDetailBio);
        tvDetailPassword = findViewById(R.id.tvDetailPassword);

        User user = (User) getIntent().getSerializableExtra("user_data");

        if (user != null) {
            tvDetailUname.setText(user.getUname());
            tvDetailBio.setText(user.getShortBio());
            if (tvDetailPassword != null) {
                tvDetailPassword.setText("Mật khẩu: " + user.getPassword());
            }

            Glide.with(this)
                    .load(user.getUrlProfile())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_delete)
                    .into(ivDetailAvatar);
        }
    }
}