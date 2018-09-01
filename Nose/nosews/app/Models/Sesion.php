<?php

namespace App\Models;
/**
 * Description of Sesion
 * Esta clase es Utlizada para realizar la conexion de
 * la tabla asociar en la base de datos, con el Web Service
 *
 * @author sam
 */

use Illuminate\Database\Eloquent\Model;

class Sesion extends Model{
    
    protected $table ='asociar';
    
    public $timestamps = true;
    //lista blanca
    protected $fillable =['id_local','id_cliente','created_at',
        'updated_at','estado'];
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

