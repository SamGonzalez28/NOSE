<?php

namespace App\Models;
/**
 * Description of Local
 * Esta clase es Utlizada para realizar la conexion de
 * la tabla local en la base de datos, con el Web Service
 *
 * @author sam
 */
use Illuminate\Database\Eloquent\Model;

class Local extends Model{
    
    protected $table ='local';
    
    public $timestamps = false; 
    //lista blanca
    protected $fillable =['nombre','direccion','RUC','telefono','external_id','estado'];
    //lista negra
    protected $guarded = ['id','clave'];
    
    //Relacion UNO a MUCHOS
    public function Menu(){
        return $this->hasMany('App\Models\Menu', 'id_local');
    }
    
    //Relacion UNO a MUCHOS
    public function Sesion(){
        return $this->hasMany('App\Models\Sesion', 'id_local');
    }
    
    //Relacion UNO a MUCHOS
    public function Cartera(){
        return $this->hasMany('App\Models\Cartera', 'id_local');
    }
    
    //Relacion UNO a MUCHOS
    public function Registros(){
        return $this->hasMany('App\Models\Registros', 'id_local');
    }
}
