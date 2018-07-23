<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Models;

/**
 * Description of Cartera
 *
 * @author sam
 */


use Illuminate\Database\Eloquent\Model;

class Cartera extends Model{
    
    protected $table ='cartera';
    
    public $timestamps = false;//porque no tengo 
    //lista blanca
    protected $fillable =['id_local','id_cliente','saldo'];
    //lista negra
    protected $guarded = ['id'];
    
    //PERTENECE A
    public function Local() {
        return $this->belongsTo('App\Models\Local', 'id_local');
    }
    
    //PERTENECE A
    public function Cliente() {
        return $this->belongsTo('App\Models\Cliente', 'id_cliente');
    }
}
