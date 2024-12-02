package model.entity;

import utils.Validacion;

public class Sesion {
    private static Sesion _sesion;
    private Usuario usuario;

    private Sesion() {}

    public static Sesion getInstance() {
        if (_sesion == null) {
            _sesion = new Sesion();
        }
        return _sesion;
    }
public static Sesion salirIstacia(){
        if (_sesion != null){
            _sesion = null;
        }
        return _sesion;
}
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (Validacion.validacionEmail(usuario.getEMAIL())) {
            usuario.setClave(Validacion.encryptClave(usuario.getClave()));
            this.usuario = usuario;
        } else {
            throw new IllegalArgumentException("Correo electrónico o contraseña no válidos.");
        }
    }


}



