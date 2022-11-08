package com.example.kr2.Clases;

public class Kanji {
    Integer id_kanji,nivel,id_tipo;
    String kanji,sig_ingle,sig_español;

    public Integer getId_kanji() {
        return id_kanji;
    }

    public void setId_kanji(Integer id_kanji) {
        this.id_kanji = id_kanji;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getSig_ingle() {
        return sig_ingle;
    }

    public void setSig_ingle(String sig_ingle) {
        this.sig_ingle = sig_ingle;
    }

    public String getSig_español() {
        return sig_español;
    }

    public void setSig_español(String sig_español) {
        this.sig_español = sig_español;
    }
}
