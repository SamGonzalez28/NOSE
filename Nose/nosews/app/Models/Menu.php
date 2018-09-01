<?php

namespace App\Models;
/**
 * Description of Menu
 * Esta clase es Utlizada para realizar la conexion de
 * la tabla menu en la base de datos, con el Web Service
 *
 * @author sam
 */
use Illuminate\Database\Eloquent\Model;

class Menu extends Model{
    
    protected $table ='menu';
    
    public $timestamps = true;
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
