package thanhdnh.ueh.edu.article_app;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ivProfile = findViewById(R.id.ivDetailProfile);
        TextView tvUname = findViewById(R.id.tvDetailUname);
        TextView tvPassword = findViewById(R.id.tvDetailPassword);
        TextView tvShortBio = findViewById(R.id.tvDetailShortBio);

        User user;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra("USER_DATA", User.class);
        } else {
            user = (User) getIntent().getSerializableExtra("USER_DATA");
        }

        if (user != null) {
            tvUname.setText(user.getUname());
            tvPassword.setText("Password: " + user.getPassword());
            tvShortBio.setText(user.getShortBio());

            Glide.with(this)
                    .load(user.getUrlProfile())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(ivProfile);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Chi tiết User");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}