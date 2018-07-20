<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Local extends Model{
    
    protected $table ='local';
    
    public $timestamps = false;//porque no tengo 
    //lista blanca
    protected $fillable =['direccion','RUC','telefono','external_id'];
    //lista negra
    protected $guarded = ['id','nombre','clave'];
    
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
}

