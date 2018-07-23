<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Local
 *
 * @author sam
 */
namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Local extends Model{
    
    protected $table ='local';
    
    public $timestamps = false;//porque no tengo 
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
}
