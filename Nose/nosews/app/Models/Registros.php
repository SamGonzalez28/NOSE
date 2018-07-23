<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Registros extends Model{
    
    protected $table ='registros';
    
    public $timestamps = false;//porque no tengo 
    //lista blanca
    protected $fillable =['id_cliente','id_menu','cantidad','fecha','valor','external_id'];
    //lista negra
    protected $guarded = ['id'];
    
    //PERTENECE A
    public function Cliente() {
        return $this->belongsTo('App\Models\Cliente', 'id_cliente');
    }
    
    //PERTENECE A
    public function Menu() {
        return $this->belongsTo('App\Models\Menu', 'id_menu');
    }
}

