<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Cliente;
use App\Models\Local;

/**
 * Description of TestController
 *
 * @author sam
 */
class TestController {

    public function test() {


        /*         * *********************************************************************
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
        
          //registra un nuevo cliente
          $cliente = new Cliente();
          $cliente-> user ='sam';
          $cliente-> clave ='a';
          $cliente-> correo ='sam@dc.g';
          $cliente-> nombres ='Samantha';
          $cliente-> apellidos ='González';
          $cliente-> estado =true;
          $cliente-> ci ='1105663847';
          $cliente-> telefono ='0997673988';
          $cliente-> direccion ='La Tebaida';
          $cliente-> external_id = utilidades\UUID::v4();
          $cliente-> save(); //modifica y guarda


          //registra una nueva local
          $local = new Local();
          $local-> nombre ='La Terraza 70';
          $local-> clave ='local';
          $local-> direccion ='Loja, frente al hospital Isidro Ayora';
          $local-> direccion ='Loja, frente al hospital Isidro Ayora';
          $local-> RUC ='1105662176001';
          $local-> estado =true;
          $local-> telefono ='09931887732';
          $local-> external_id = utilidades\UUID::v4();
          $local-> save(); //modifica y guarda

          //registrar cartera
          $cartera = new \App\Models\Cartera();
          $cartera->saldo = 500.00;
          $cartera->Local()->associate($local);//asocio las tablas relacionadas
          $cartera->Cliente()->associate($cliente);//asocio las tablas relacionadas
          $cartera->save();

          //registrar menu
          $menu = new \App\Models\Menu();
          $menu->tipo ='DESAYUNO';
          $menu->descripcion ='Café con bollo y queso';
          $menu->precio = 2.5;
          $menu-> external_id = utilidades\UUID::v4();
          $menu->Local()->associate($local);//asocio las tablas relacionadas

          $menu->save();

          //registrar registros
          $registro = new \App\Models\Registros();
          $registro->cantidad = 2;
          $registro->valor = 5.00;
          $registro->external_id = utilidades\UUID::v4();
          $registro->Cliente()->associate($cliente);//asocio las tablas relacionadas
          $registro->Menu()->associate($menu);//asocio las tablas relacionadas
          $registro->save();

         
        //sleep(3);
        //registrar registros
        $sesion = new \App\Models\Sesion();
        $sesion->estado = true;
        
        $sesion->Cliente()->associate(1); //asocio las tablas relacionadas
        $sesion->Local()->associate(1); //asocio las tablas relacionadas
        $sesion->save();
        
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
