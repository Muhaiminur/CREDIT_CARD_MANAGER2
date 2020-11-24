package com.debit_credit_card.creditcardmanager.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.debit_credit_card.creditcardmanager.CARD_VALIDATION.CardCompany;
import com.debit_credit_card.creditcardmanager.CARD_VALIDATION.CardValidationResult;
import com.debit_credit_card.creditcardmanager.DATABASE.REPOSITORY.CARD_REPOSITORY;
import com.debit_credit_card.creditcardmanager.DATABASE.TABLE.CARD_TABLE;
import com.debit_credit_card.creditcardmanager.LIBRARY.KeyWord;
import com.debit_credit_card.creditcardmanager.LIBRARY.OnSwipeTouchListener;
import com.debit_credit_card.creditcardmanager.LIBRARY.UTILITY;
import com.debit_credit_card.creditcardmanager.R;
import com.debit_credit_card.creditcardmanager.databinding.ActivityAddCardBinding;

import java.util.HashMap;

public class AddCard extends AppCompatActivity {

    Context context;
    CARD_REPOSITORY cardRepository;
    CardCompany result;
    ActivityAddCardBinding addCardBinding;
    UTILITY utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getText(R.string.add_cardnew_title_string));
            context = AddCard.this;
            addCardBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_card);
            cardRepository = new CARD_REPOSITORY(getApplication());
            utility = new UTILITY(context);
            edittext_check();
            button_work();
            addcard_validation();
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public void edittext_check() {
        try {
            addCardBinding.addcardName.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    if (addCardBinding.cardFront.getVisibility() == View.GONE) {
                        card_front();
                    }
                    addCardBinding.addcardResultName.setText(addCardBinding.addcardName.getEditableText().toString());
                    if (addCardBinding.addcardName.getEditableText().toString().length() == 0) {
                        addCardBinding.addcardResultName.setText(context.getResources().getText(R.string.add_name_string));
                        addCardBinding.addcardName.setError(context.getResources().getText(R.string.add_name_string));
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            addCardBinding.addcardNumber.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    if (addCardBinding.cardFront.getVisibility() == View.GONE) {
                        card_front();
                    }
                    StringBuilder s = new StringBuilder(cs.toString());
                    for (int i = 4; i < s.length(); i += 5) {
                        s.insert(i, " ");
                    }
                    addCardBinding.addcardResultNumber.setText(s.toString());
                    if (addCardBinding.addcardNumber.getEditableText().toString().length() == 0) {
                        addCardBinding.addcardResultNumber.setText(context.getResources().getText(R.string.sample_number_string));
                        addCardBinding.addcardNumber.setError(context.getResources().getText(R.string.add_number_string));
                    }
                    if (addCardBinding.addcardNumber.getEditableText().toString().length() == 16) {
                        //CardValidationResult result = isValid(cs.toString());
                        //System.out.println(result.isValid() + " : " + (result.isValid() ? result.getCardType().getIssuerName() : "") + " : " + result.getMessage());
                        result = CardCompany.gleanCompany(cs.toString());
                        if (result != null) {
                            if (result.getIssuerName().equalsIgnoreCase("VISA")) {
                                addCardBinding.addcardResultLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_visacard));
                            } else if (result.getIssuerName().equalsIgnoreCase("MASTER")) {
                                addCardBinding.addcardResultLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_mastercard));
                            } else if (result.getIssuerName().equalsIgnoreCase("AMEX")) {
                                addCardBinding.addcardResultLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_amexcard));
                            } else if (result.getIssuerName().equalsIgnoreCase("DINERS")) {
                                addCardBinding.addcardResultLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dinnerscard));
                            } else if (result.getIssuerName().equalsIgnoreCase("DISCOVER")) {
                                addCardBinding.addcardResultLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_discovercard));
                            } else if (result.getIssuerName().equalsIgnoreCase("JCB")) {
                                addCardBinding.addcardResultLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_jcbcard));
                            }
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            addCardBinding.addcardExpire.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    if (addCardBinding.cardFront.getVisibility() == View.GONE) {
                        card_front();
                    }
                    StringBuilder s = new StringBuilder(cs.toString());
                    for (int i = 2; i < s.length(); i += 3) {
                        s.insert(i, "/");
                    }
                    addCardBinding.addcardResultExpire.setText(s.toString());
                    //addCardBinding.addcardExpire.setText(s.toString());
                    if (addCardBinding.addcardExpire.getEditableText().toString().length() == 0) {
                        addCardBinding.addcardResultExpire.setText(context.getResources().getText(R.string.sample_expire_string));
                        addCardBinding.addcardExpire.setError(context.getResources().getText(R.string.add_expire_string));
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            addCardBinding.addcardCvc.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    card_back();
                    addCardBinding.addcardResultCvc.setText(addCardBinding.addcardCvc.getEditableText().toString());
                    if (addCardBinding.addcardCvc.getEditableText().toString().length() == 0) {
                        addCardBinding.addcardResultCvc.setText(context.getResources().getText(R.string.sample_cvc_string));
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            /*addCardBinding.addcardNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    card_front();
                }
            });
            addCardBinding.addcardName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    card_front();
                }
            });
            addCardBinding.addcardExpire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    card_front();
                }
            });
            addCardBinding.addcardCvc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    card_back();
                }
            });*/
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    void button_work() {
        try {
            addCardBinding.wholeview.setOnTouchListener(new OnSwipeTouchListener(context) {
                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    try {
                        card_front();
                    } catch (Exception e) {
                        Log.d("Error Line Number", Log.getStackTraceString(e));
                    }
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    try {
                        card_back();
                    } catch (Exception e) {
                        Log.d("Error Line Number", Log.getStackTraceString(e));
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }

    }

    public void card_front() {
        try {
            if (addCardBinding.cardFront.getVisibility() == View.GONE) {
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(addCardBinding.cardBack, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(addCardBinding.cardFront, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        addCardBinding.cardBack.setVisibility(View.GONE);
                        addCardBinding.cardFront.setVisibility(View.VISIBLE);
                        oa2.start();
                    }
                });
                oa1.start();
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public void card_back() {
        try {
            if (addCardBinding.cardBack.getVisibility() == View.GONE) {
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(addCardBinding.cardFront, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(addCardBinding.cardBack, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        addCardBinding.cardFront.setVisibility(View.GONE);
                        addCardBinding.cardBack.setVisibility(View.VISIBLE);
                        oa2.start();
                    }
                });
                oa1.start();
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public void addcard_validation() {
        try {
            addCardBinding.addcardDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (!TextUtils.isEmpty(addCardBinding.addcardNumber.getEditableText().toString())) {
                            if (!TextUtils.isEmpty(addCardBinding.addcardName.getEditableText().toString())) {
                                if (!TextUtils.isEmpty(addCardBinding.addcardExpire.getEditableText().toString())) {
                                    cardRepository.insert_single_card_data(new CARD_TABLE(addCardBinding.addcardName.getEditableText().toString(), addCardBinding.addcardNumber.getEditableText().toString(), addCardBinding.addcardExpire.getEditableText().toString(), addCardBinding.addcardCvc.getEditableText().toString(), result.getIssuerName(), "blue", ""));
                                    showaddDialog(context.getResources().getString(R.string.add_card_confirmed));
                                } else {
                                    addCardBinding.addcardExpire.setError(context.getResources().getText(R.string.add_expire_string));
                                }
                            } else {
                                addCardBinding.addcardName.setError(context.getResources().getText(R.string.add_name_string));
                            }
                        } else {
                            addCardBinding.addcardNumber.setError(context.getResources().getText(R.string.add_number_string));
                        }
                    } catch (Exception e) {
                        Log.d("Error Line Number", Log.getStackTraceString(e));
                    }
                }
            });

        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    /**
     * Validator for credit card numbers
     * Checks validity and returns card type
     */
    public static CardValidationResult isValid(final String cardIn) {
        String card = cardIn.replaceAll("[^0-9]+", ""); // remove all non-numerics
        if ((card == null) || (card.length() < 13) || (card.length() > 19)) {
            return new CardValidationResult(card, "failed length check");
        }

        if (!luhnCheck(card)) {
            return new CardValidationResult(card, "failed luhn check");
        }

        CardCompany cc = CardCompany.gleanCompany(card);
        if (cc == null) return new CardValidationResult(card, "failed card company check");

        return new CardValidationResult(card, cc);
    }

    /**
     * Checks for a valid credit card number.
     *
     * @param cardNumber Credit Card Number.
     * @return Whether the card number passes the luhnCheck.
     */
    protected static boolean luhnCheck(String cardNumber) {
        // number must be validated as 0..9 numeric first!!
        int digits = cardNumber.length();
        int oddOrEven = digits & 1;
        long sum = 0;
        for (int count = 0; count < digits; count++) {
            int digit = 0;
            try {
                digit = Integer.parseInt(cardNumber.charAt(count) + "");
            } catch (NumberFormatException e) {
                return false;
            }

            if (((count & 1) ^ oddOrEven) == 0) { // not
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return (sum == 0) ? false : (sum % 10 == 0);
    }

    public void showaddDialog(String message) {
        try {
            HashMap<String, Integer> screen = utility.getScreenRes();
            int width = screen.get(KeyWord.SCREEN_WIDTH);
            int height = screen.get(KeyWord.SCREEN_HEIGHT);
            int mywidth = (width / 10) * 7;
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.dialog_toast);
            TextView tvMessage = dialog.findViewById(R.id.tv_message);
            Button btnOk = dialog.findViewById(R.id.btn_ok);
            tvMessage.setText(message);
            LinearLayout ll = dialog.findViewById(R.id.dialog_layout_size);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll.getLayoutParams();
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            params.width = mywidth;
            ll.setLayoutParams(params);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
