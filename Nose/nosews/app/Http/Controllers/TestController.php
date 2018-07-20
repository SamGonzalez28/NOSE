<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;
use App\Models\Cliente;


/**
 * Description of TestController
 *
 * @author sam
 */
class TestController {
    
    public function test (){
        
        
        /***********************************************************************
         * esto sirve para ver el numero de registros en las tablas
        $listaA = Administrador::all();
        $listaB = Noticia::all();
        $listac = Comentario::all();
        $listaD = Imagen::all();
        if($listac->count() > 0){
        return response()->json(['test'=>"OK", "mensaje" => "tiene ".$listac->count()." datos"], 200);
         }else{
            return response()->json(['test'=>"NO", "mensaje" => "no tiene datos"], 200);
        }
         * 
         */
        
        //registra un nuevo admin
        $cliente = new Cliente();
        
        $cliente-> user ='sam';
        $cliente-> clave ='a';
        $cliente-> nombres ='Samantha';
        $cliente-> apellidos ='González';
        $cliente-> estado =true;
        $cliente-> ci ='1105663247';
        $cliente-> external_id = utilidades\UUID::v4();
        $cliente-> save(); //modifica y guarda
        
        /*
        //registra una nueva noticia
        $not = new Noticia();
        
        $not-> titulo ='Asesinato';
        $not-> texto ='Mataron a alguien';
        $not->Administrador()->associate(5);//asocia cuando hay relacion
        $not-> save(); //modifica y guarda
       */
        
        /*
        $imagen = new Imagen();
        $imagen->ruta = "images/diosito.png";
        $imagen->portada = true;
        $imagen->Noticia()->associate(2);//asocio las tablas relacionadas
        $imagen->save();
        /*
        $comentario = new Comentario();
        $comentario->comentario = "que pena";
        $comentario->usuario = "Wargosh";
        $comentario->estado = true;
        $comentario->Noticia()->associate(2);//asocio las tablas relacionadas
        $comentario->save();
         */ 
        
        /*
        $admin = Administrador::find(5);
        $notici = $admin->Noticias;
        
        foreach ($notici as $data){ 
            echo '<div>'.$data->titulo.'</div>';
        }
        
        $notic = Noticia::find(3);
        if($notic){
            echo '<br><div>'.$notic->Administrador->usuario.'</div>' ;
        } else {
            echo 'Registro no encontrado';
        }
         * 
         */
    }
}
