package com.example.practica3.POJO;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public enum Comparar{
    NOMBRE(new Comparator() {
        @Override
        public int compare(Object o, Object t1) {
            Contacto contacto =(Contacto) o;
            Contacto contacto2 = (Contacto) t1;

            return contacto.getNombre().toLowerCase(Locale.ROOT).compareTo(contacto2.getNombre().toLowerCase(Locale.ROOT));

        }
    }),
    APELLIDO(new Comparator() {
        @Override
        public int compare(Object o, Object t1) {
            Contacto contacto = (Contacto) o;
            Contacto contacto2 = (Contacto) t1;

            return contacto.getApellidos().toLowerCase(Locale.ROOT).compareTo(contacto2.getApellidos().toLowerCase(Locale.ROOT));
        }
    }),
    EDAD(new Comparator() {
        @Override
        public int compare(Object o, Object t1) {
            Contacto contacto = (Contacto) o;
            Contacto contacto2 = (Contacto) t1;

            return (contacto.getEdad() - contacto2.getEdad());
        }
    }),
    TELEFONO(new Comparator() {
        @Override
        public int compare(Object o, Object t1) {
            Contacto contacto = (Contacto) o;
            Contacto contacto2 = (Contacto) t1;
            return String.valueOf(contacto.getTelefono()).compareTo(String.valueOf(contacto2.getTelefono()));
        }
    });

    Comparator comparator;

    Comparar(Comparator comparator) {
        this.comparator = comparator;
    }

    public Comparator getComparator() {
        return comparator;
    }
}
