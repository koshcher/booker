package booker.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import booker.mobile.ui.fragments.BookListFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layoutContainer, new BookListFragment())
                .commit();
    }
}