package booker.mobile.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import booker.mobile.R;
import booker.mobile.api.Api;
import booker.mobile.api.lib.ApiResult;
import booker.mobile.api.models.LoginBody;
import booker.mobile.api.models.RegisterBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthFragment extends Fragment {

    TextView authFormType;
    EditText emailInput;
    EditText passwordInput;
    TextView confirmPasswordLabel;
    EditText confirmPasswordInput;
    Button confirmBtn;
    Button switchFormType;

    boolean isLoginForm = true;

    public AuthFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_auth, container, false);

        confirmPasswordLabel = view.findViewById(R.id.confirmPasswordLabel);
        authFormType = view.findViewById(R.id.authFormType);
        emailInput = view.findViewById(R.id.emailInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        confirmPasswordInput = view.findViewById(R.id.confirmPasswordInput);
        confirmBtn = view.findViewById(R.id.confirmBtn);
        switchFormType = view.findViewById(R.id.switchFormType);

        confirmBtn.setOnClickListener(v -> {
            if(isLoginForm) {
                login();
            } else {
                register();
            }
        });

        switchFormType.setOnClickListener(v -> {
            if(isLoginForm) {
                switchToRegister();
                isLoginForm = false;
            } else  {
                switchToLogin();
                isLoginForm = true;
            }
        });

        return view;
    }

    private void switchToRegister() {
        authFormType.setText("Register");
        switchFormType.setText("Already have an account? Login.");
        confirmBtn.setText("Register");
        confirmPasswordLabel.setVisibility(View.VISIBLE);
        confirmPasswordInput.setVisibility(View.VISIBLE);
    }

    private void switchToLogin() {
        authFormType.setText("Login");
        switchFormType.setText("Don't have an account? Register.");
        confirmBtn.setText("Login");
        confirmPasswordLabel.setVisibility(View.INVISIBLE);
        confirmPasswordInput.setVisibility(View.INVISIBLE);
    }

    private void register() {
        String email = emailInput.getText().toString().trim();
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(),"Email isn't correct!", Toast.LENGTH_LONG).show();
            return;
        }

        String password = passwordInput.getText().toString().trim();
        if(password.isEmpty()) {
            Toast.makeText(getContext(),"Password can't be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        String confirmPassword = confirmPasswordInput.getText().toString().trim();
        if(confirmPassword.isEmpty()) {
            Toast.makeText(getContext(),"Confirm password can't be empty!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!confirmPassword.equals(password)) {
            Toast.makeText(getContext(),"Confirm password doesn't match password!", Toast.LENGTH_LONG).show();
            return;
        }

        Api.auth()
                .passwordRegister(new RegisterBody(email, password, confirmPassword))
                .enqueue(new AuthCallback());
    }

    private void login() {
        String email = emailInput.getText().toString().trim();
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(),"Email isn't correct!", Toast.LENGTH_LONG).show();
            return;
        }

        String password = passwordInput.getText().toString().trim();
        if(password.isEmpty()) {
            Toast.makeText(getContext(),"Password can't be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        Api.auth().passwordLogin(new LoginBody(email, password)).enqueue(new AuthCallback());
    }


    private class AuthCallback implements Callback<ApiResult<String>> {

        @Override
        public void onResponse(Call<ApiResult<String>> call, Response<ApiResult<String>> response) {
            ApiResult<String> result = response.body();
            if(result == null) {
                Toast.makeText(getContext(),"Something went wrong. Try later.", Toast.LENGTH_LONG).show();
                return;
            }

            if(result.getError() != null) {
                Toast.makeText(getContext(), result.getError().getMessage(), Toast.LENGTH_LONG).show();
                return;
            }

            Api.setAccessToken(result.getData());
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new BookListFragment()).commit();
        }

        @Override
        public void onFailure(Call<ApiResult<String>> call, Throwable t) {
            Toast.makeText(getContext(), "Something went wrong. Try later.", Toast.LENGTH_LONG).show();
        }
    }
}