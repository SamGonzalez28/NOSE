<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Cliente;
use App\Models\Local;
use App\Models\Sesion;
use Illuminate\Http\Request;

/**
 * Description of SesionController
 *
 * @author sam
 */
class SesionController extends Controller {

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = Cliente::where("external_id", $data["cliente"])->first();
                $local = Local::where("external_id", $data["local"])->first();
                if ($cliente == true && $local == true) {
                    $sesion = new Sesion();
                    $sesion->estado = true;
                    $sesion->external_id = utilidades\UUID::v4();
                    $sesion->local()->associate($local);
                    $sesion->cliente()->associate($cliente);
                    $sesion->save();
                    
                    return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
                } else{
                    return response()->json(["mensaje" => "Referencia Incorrecta", "siglas" => "RI"], 400);
                }
                
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }
    
     public function eliminar(Request $request,$external_id){
        $asoObj= Sesion::where("external_id",$external_id)-> first();
        if ($asoObj){
            if($request->isJson()){
                
                $session= Sesion::find($asoObj->id);
               
                    $session->estado = false;
                $session->save();
                return response()-> json(["mensaje"=> "Operacion exitosa","siglas"=> "OE"],200);
                
            }else{
                return response()-> json(["mensaje"=> "La data no tiene el formato deseado","siglas"=> "DNF"],400);
            }
            
        }else{
            return response()-> json(["mensaje"=> "No se encontro ningun dato","siglas"=> "NDE"],204);
        }
    }

}
