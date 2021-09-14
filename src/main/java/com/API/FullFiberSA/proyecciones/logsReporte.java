package com.API.FullFiberSA.proyecciones;

import java.util.Date;

public interface logsReporte {
    Long getid_log_cambio();
    String getcomentario_cambio();
    String getfecha_cambio_log();
    String getnombre_admin();
    Long getid_campanha_anterior();
    Long getid_campanha_nueva();
    String getnombre_cliente();

}
