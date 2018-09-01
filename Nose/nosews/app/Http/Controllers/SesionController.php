<?php

namespace App\Http\Controllers;

use App\Models\Cliente;
use App\Models\Local;
use App\Models\Sesion;
use App\Models\Cartera;
use Illuminate\Http\Request;

/**
 * Description of SesionController
 * Clase usada para controlar las funciones que se aplican a la tabla asociar
 * @author sam
 */
class SesionController extends Controller {

    /**
     * Funcion que se encarga de registrar una Sesion
     * @param Request $request
     * @return type
     */
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
                    $sesion->Local()->associate($local);
                    $sesion->Cliente()->associate($cliente);
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
    
    /**
     * Funcion para eliminar de manera logica, una sesion
     * @param Request $request
     * @param type $external_id external de la sesion que se quiere eliminar
     * @return type
     */
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
    
    /**
     * Funcion para listar sesiones de acuerdo al local al que estan asociados
     * @param type $external_id del local
     * @return type
     */
    public function listarporLocal($external_id) {
        $local = Local::where("external_id", $external_id)->first();
        if ($local) {
            $lista = Sesion::where('id_local', $local->id)
            ->join('cliente', 'cliente.id','=','asociar.id_cliente')
            ->get();
            foreach ($lista as $item) {
                $cartera = \App\Models\Cartera::where('id_local', $item->id_local)->first();
                $data[] = ["cliente" => $item->user,
                "nombres"=>$item->nombres,
                "apellidos"=>$item->apellidos,
                "saldo"=>$cartera->saldo];
            }
            return response()->json($data, 200);
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }
    
   

}
