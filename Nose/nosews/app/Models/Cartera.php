<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Cartera extends Model{
    
    protected $table ='cartera';
    
    public $timestamps = true;//porque no tengo 
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

