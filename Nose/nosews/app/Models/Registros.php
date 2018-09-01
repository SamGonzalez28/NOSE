<?php

namespace App\Models;
/**
 * Description of Registros
 * Esta clase es Utlizada para realizar la conexion de
 * la tabla registros en la base de datos, con el Web Service
 *
 * @author sam
 */
use Illuminate\Database\Eloquent\Model;

class Registros extends Model{
    
    protected $table ='registros';
    
    public $timestamps = false; 
    //lista blanca
    protected $fillable =['id_cliente','id_menu','id_local','cantidad','fecha','valor','external_id'];
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
    
    //PERTENECE A
    public function Local() {
        return $this->belongsTo('App\Models\Local', 'id_local');
    }
}

