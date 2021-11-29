package com.example.practica3.POJO;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {
    //*Solo voy a poner los atributos que se reflejen en el Text View
    private String nombre;
    private String apellidos;
    private int telefono;
    private int edad;

    public Contacto(String nombre, String apellidos, int telefono, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.edad = edad;
    }

    public Contacto(String nombre, String apellidos, int telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Contacto(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = 0;
        this.edad = 0;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre+" "+apellidos+" :"+telefono;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellidos);
        dest.writeInt(this.telefono);
        dest.writeInt(this.edad);
    }

    public void readFromParcel(Parcel source) {
        this.nombre = source.readString();
        this.apellidos = source.readString();
        this.telefono = source.readInt();
        this.edad = source.readInt();
    }

    protected Contacto(Parcel in) {
        this.nombre = in.readString();
        this.apellidos = in.readString();
        this.telefono = in.readInt();
        this.edad = in.readInt();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel source) {
            return new Contacto(source);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };
}
