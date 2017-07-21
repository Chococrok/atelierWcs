package com.wcs.atelierexceptionnel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by apprenti on 09/05/17.
 */

public class Banane implements Parcelable {
    int taille;
    int couleur;
    boolean estMure;

    public Banane(int taille, int couleur, boolean estMure) {
        this.taille = taille;
        this.couleur = couleur;
        this.estMure = estMure;
    }

    protected Banane(Parcel in) {
        taille = in.readInt();
        couleur = in.readInt();
        estMure = in.readByte() != 0;
    }

    public static final Creator<Banane> CREATOR = new Creator<Banane>() {
        @Override
        public Banane createFromParcel(Parcel in) {
            return new Banane(in);
        }

        @Override
        public Banane[] newArray(int size) {
            return new Banane[size];
        }
    };

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public boolean isEstMure() {
        return estMure;
    }

    public void setEstMure(boolean estMure) {
        this.estMure = estMure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(taille);
        dest.writeInt(couleur);
        dest.writeByte((byte) (estMure ? 1 : 0));
    }
}
