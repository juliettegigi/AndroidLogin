package com.softulp.app.login.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.softulp.app.login.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;
    private static SharedPreferences conectar(Context context){
        if(sp==null)
            sp=context.getSharedPreferences("datos",0);
        return sp;
    }

    public static void guardar(Context context,Usuario user){
        SharedPreferences sp=conectar(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putLong("dni",user.getDni());
        editor.putString("apellido", user.getApellido());
        editor.putString("nombre",user.getNombre());
        editor.putString("email",user.getEmail());
        editor.putString("pass",user.getPass());
        editor.commit();
    }
    public static Usuario leer(Context context){
        SharedPreferences sp=conectar(context);
        Long dni=sp.getLong("dni",-1);
        String apellido=sp.getString("apellido",null);
        String nombre=sp.getString("nombre",null);
        String email=sp.getString("email",null);
        String pass=sp.getString("pass",null);
        return new Usuario(dni,apellido,nombre,email,pass);
    }

    public static  Usuario login(Context context,String email,String pass){
        Usuario usuario=leer(context);
        Log.d("salida",usuario.getEmail());
        Log.d("salida",email);
        if(usuario.getDni()!=-1 && usuario.getEmail().equals(email) && usuario.getPass().equals(pass)){
            return usuario;
        }
        return null;
    }
}
