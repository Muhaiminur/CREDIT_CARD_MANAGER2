package com.debit_credit_card.creditcardmanager.DATABASE.TABLE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_table")
public class CARD_TABLE {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "card_id")
    private int card_Id;

    @NonNull
    @ColumnInfo(name = "card_name")
    private String card_name;

    @NonNull
    @ColumnInfo(name = "card_number")
    private String card_number;

    @NonNull
    @ColumnInfo(name = "card_expire")
    private String card_expire;

    @ColumnInfo(name = "cart_cvv")
    private String card_cvv;

    @ColumnInfo(name = "card_type")
    private String card_type;

    @ColumnInfo(name = "card_color")
    private String card_color;

    @ColumnInfo(name = "card_amount")
    private String card_amount;

    public CARD_TABLE() {
    }

    public CARD_TABLE(@NonNull String card_name, @NonNull String card_number, @NonNull String card_expire, String card_cvv, String card_type, String card_color, String card_amount) {
        this.card_name = card_name;
        this.card_number = card_number;
        this.card_expire = card_expire;
        this.card_cvv = card_cvv;
        this.card_type = card_type;
        this.card_color = card_color;
        this.card_amount = card_amount;
    }

    @NonNull
    public int getCard_Id() {
        return card_Id;
    }

    public void setCard_Id(@NonNull int card_Id) {
        this.card_Id = card_Id;
    }

    @NonNull
    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(@NonNull String card_name) {
        this.card_name = card_name;
    }

    @NonNull
    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(@NonNull String card_number) {
        this.card_number = card_number;
    }

    @NonNull
    public String getCard_expire() {
        return card_expire;
    }

    public void setCard_expire(@NonNull String card_expire) {
        this.card_expire = card_expire;
    }

    public String getCard_cvv() {
        return card_cvv;
    }

    public void setCard_cvv(String card_cvv) {
        this.card_cvv = card_cvv;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_color() {
        return card_color;
    }

    public void setCard_color(String card_color) {
        this.card_color = card_color;
    }

    public String getCard_amount() {
        return card_amount;
    }

    public void setCard_amount(String card_amount) {
        this.card_amount = card_amount;
    }

    @Override
    public String toString() {
        return "CARD_TABLE{" +
                "card_Id='" + card_Id + '\'' +
                ", card_name='" + card_name + '\'' +
                ", card_number='" + card_number + '\'' +
                ", card_expire='" + card_expire + '\'' +
                ", card_cvv='" + card_cvv + '\'' +
                ", card_type='" + card_type + '\'' +
                ", card_color='" + card_color + '\'' +
                ", card_amount='" + card_amount + '\'' +
                '}';
    }
}
