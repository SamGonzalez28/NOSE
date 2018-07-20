<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Sesion extends Model{
    
    protected $table ='sesion';
    
    public $timestamps = false;//porque no tengo 
    //lista blanca
    protected $fillable =['id_local','id_cliente'];
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
