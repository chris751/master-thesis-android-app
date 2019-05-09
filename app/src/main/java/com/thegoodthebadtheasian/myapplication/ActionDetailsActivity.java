package com.thegoodthebadtheasian.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thegoodthebadtheasian.myapplication.models.Action;
import com.thegoodthebadtheasian.myapplication.models.actionmodels.GoogleAnalytics;
import com.thegoodthebadtheasian.myapplication.models.actionmodels.Notification;
import com.thegoodthebadtheasian.myapplication.models.actionmodels.SMS;

import org.w3c.dom.Text;

public class ActionDetailsActivity extends AppCompatActivity {

    public static final String ACTION_DETAILS_RESULT_EXTRAS = "ACTION_DETAILS_RESULT_EXTRAS";

    private Button confirmBtn;

    private int actionType;

    private Action mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_details);

        mAction = (Action) getIntent().getSerializableExtra(DeviceDetailsActivity.ACTION_EXTRA);

        actionType = getIntent().getIntExtra(AddActionActivity.ACTION_TYPE_EXTRA, -99);
        if(actionType == -99){
            Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT);
        }

        setupLayout(actionType);

        confirmBtn = findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveResultAndFinish();
            }
        });
    }

    private void setupLayout(int actionType){
        switch (actionType) {
            case AddActionActivity.SMS_ACTION:
                setContentView(R.layout.activity_action_details_sms);
                if(mAction.getSms() != null){
                    TextView phoneNumberInput = findViewById(R.id.phoneNumberInput);
                    phoneNumberInput.setText(mAction.getSms().getPhoneNumber());
                    TextView messageInput = findViewById(R.id.messageInput);
                    messageInput.setText(mAction.getSms().getMessage());
                }
                break;
            case AddActionActivity.GA_ACTION:
                setContentView(R.layout.activity_action_details_ga);
                if(mAction.getGoogleAnalytics() != null) {
                    TextView eventCategory = findViewById(R.id.eventCategoryInput);
                    TextView eventAction = findViewById(R.id.eventActionInput);
                    TextView eventLabel = findViewById(R.id.eventLabelInput);
                    TextView eventValue = findViewById(R.id.eventValueInput);

                    eventCategory.setText(mAction.getGoogleAnalytics().getEventCategory());
                    eventAction.setText(mAction.getGoogleAnalytics().getEventAction());
                    eventLabel.setText(mAction.getGoogleAnalytics().getEventLabel());
                    eventValue.setText(mAction.getGoogleAnalytics().getEventValue()+"");
                }
                break;
            case AddActionActivity.NOTIFICATION_ACTION:
                setContentView(R.layout.activity_action_details_notification);
                if(mAction.getNotification() != null){
                    TextView title = findViewById(R.id.titleInput);
                    TextView message = findViewById(R.id.messageInput);
                    TextView value = findViewById(R.id.valueInput);

                    title.setText(mAction.getNotification().getTitle());
                    message.setText(mAction.getNotification().getMessage());
                    value.setText(mAction.getNotification().getValue()+"");
                }
                break;
            default:
                return;
        }
    }

    private Action getSmsResult(){
        TextView phoneNumberInput = findViewById(R.id.phoneNumberInput);
        TextView messageInput = findViewById(R.id.messageInput);

        SMS smsResult = new SMS();
        smsResult.setPhoneNumber(phoneNumberInput.getText()+"");
        smsResult.setMessage(messageInput.getText()+"");
        Action actionResult = mAction;

        actionResult.setSms(smsResult);

        return actionResult;
    }

    private Action getGaResult() {
        TextView categoryInput = findViewById(R.id.eventCategoryInput);
        TextView actionInput = findViewById(R.id.eventActionInput);
        TextView labelInput = findViewById(R.id.eventLabelInput);
        TextView valueInput = findViewById(R.id.eventValueInput);

        GoogleAnalytics gaResult = new GoogleAnalytics();
        gaResult.setEventCategory(categoryInput.getText()+"");
        gaResult.setEventAction(actionInput.getText()+"");
        gaResult.setEventLabel(labelInput.getText()+"");
        gaResult.setEventValue(Integer.parseInt(valueInput.getText()+""));

        Action actionResult = mAction;
        actionResult.setGoogleAnalytics(gaResult);

        return actionResult;
    }

    private Action getNotificationResult() {
        TextView titleInput = findViewById(R.id.titleInput);
        TextView messageInput = findViewById(R.id.messageLabel);
        TextView valueInput = findViewById(R.id.valueInput);

        Notification notificationResult = new Notification();
        notificationResult.setTitle(titleInput.getText()+"");
        notificationResult.setMessage(messageInput.getText()+"");
        notificationResult.setValue(Integer.parseInt(valueInput.getText()+""));

        Action actionResult = mAction;
        actionResult.setNotification(notificationResult);

        return actionResult;
    }

    private Action getResult(){
        Action actionResult;
        switch (actionType) {
            case AddActionActivity.SMS_ACTION:
                actionResult = getSmsResult();
                break;
            case AddActionActivity.GA_ACTION:
                actionResult = getGaResult();
                break;
            case AddActionActivity.NOTIFICATION_ACTION:
                actionResult = getNotificationResult();
                break;
            default:
                actionResult = mAction;
        }
        return actionResult;
    }

    private void saveResultAndFinish(){
        Intent returnIntent = new Intent();
        Action resultData = getResult();
        returnIntent.putExtra(ACTION_DETAILS_RESULT_EXTRAS, resultData);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
