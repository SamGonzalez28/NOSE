<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Menu extends Model{
    
    protected $table ='menu';
    
    public $timestamps = true;//porque tengo 
    //lista blanca
    protected $fillable =['tipo','precio','descripcion','id_local','external_id', 'updated_at', 'created_at'];
    //lista negra
    protected $guarded = ['id'];
    
    //PERTENECE A
    public function Local() {
        return $this->belongsTo('App\Models\Local', 'id_local');
    }
    
    //Relacion UNO a MUCHOS
    public function Registros(){
        return $this->hasMany('App\Models\Registros', 'id_menu');
    }
    
}
