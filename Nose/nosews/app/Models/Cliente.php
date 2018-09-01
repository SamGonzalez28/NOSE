<?php

namespace App\Models;
/**
 * Description of CLiente
 * Esta clase es Utlizada para realizar la conexion de
 * la tabla cliente en la base de datos, con el Web Service
 *
 * @author sam
 */

use Illuminate\Database\Eloquent\Model;

class Cliente extends Model{
    
    protected $table ='cliente';
    
    public $timestamps = false;
    //lista blanca
    protected $fillable =['nombres','apellidos','ci','telefono','direccion','external_id','correo','user','clave','estado'];
    //lista negra
    protected $guarded = ['id'];
    
    //Relacion UNO a MUCHOS
    public function Menu(){
        return $this->hasMany('App\Models\Menu', 'id_cliente');
    }
    
    //Relacion UNO a MUCHOS
    public function Registros(){
        return $this->hasMany('App\Models\Registros', 'id_cliente');
    }
    
    //Relacion UNO a MUCHOS
    public function Sesion(){
        return $this->hasMany('App\Models\Sesion', 'id_cliente');
    } 
    
    //Relacion UNO a MUCHOS
    public function Cartera(){
        return $this->hasMany('App\Models\Cartera', 'id_cliente');
    } 
}

