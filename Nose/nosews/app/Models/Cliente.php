<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Cliente extends Model{
    
    protected $table ='local';
    
    public $timestamps = false;//porque no tengo 
    //lista blanca
    protected $fillable =['nombres','apellidos','ci','telefono','direccion','external_id'];
    //lista negra
    protected $guarded = ['id','correo'];
    
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

