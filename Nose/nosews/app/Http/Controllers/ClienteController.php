<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Cliente;
use Illuminate\Http\Request;

/**
 * Description of ClienteController
 *
 * @author AlejandroC
 */
class ClienteController {

    private $external_id;

    public function __construct() {
        
    }

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $client = new Cliente();
                $client->user = $data["user"];
                $client->clave = $data["clave"];
                $client->nombres = $data["nombres"];
                $client->apellidos = $data["apellidos"];
                $client->ci = $data["ci"];
                $client->correo = $data["correo"];
                $client->direccion = $data["direccion"];
                $client->telefono = $data["telefono"];
                $client->external_id = utilidades\UUID::v4();

                $client->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }

    public function inicioSesion(Request $request) {

        try {
            $data = $request->json()->all();
            $cliente = Cliente::where("user", $data["user"])->where("clave", $data["clave"])->where("estado", true)->first();

            if ($cliente) {
                return response()->json(["user" => $cliente->user,
                            "id" => $cliente->external_id,
                            "nombre" => $cliente->nombre,
                            "token" => base64_encode($cliente->external_id . '--' . $cliente->user),
                            "mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
            }
        } catch (\Exception $exc) {
            return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
        }
    }

    public function modificar(Request $request, $external_id) {
        $clienteObjeto = Cliente::where("external_id", $external_id)->first();
        if ($clienteObjeto) {
            if ($request->isJson()) {
                $data = $request->json()->all();
                $client = Cliente::find($clienteObjeto->id_cliente);
                if (isset($data["user"]))
                    $client->user = $data["user"];
                if (isset($data["clave"]))
                    $client->clave = $data["clave"];
                if (isset($data["nombres"]))
                    $client->nombres = $data["nombres"];
                if (isset($data["apellidos"]))
                    $client->nombre = $data["apellidos"];
                if (isset($data["correo"]))
                    $client->nombre = $data["correo"];
                if (isset($data["direccion"]))
                    $client->nombre = $data["direccion"];
                if (isset($data["telefono"]))
                    $client->nombre = $data["telefono"];

                $client->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            }else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }
    
    public function eliminar(Request $request,$external_id){
        $clientObjeto=Cliente::where("external_id",$external_id)-> first();
        if ($clientObjeto){
            if($request->isJson()){
                $data=$request->json()->all();
                $client= Cliente::find($clientObjeto->id_cliente);
               if (isset($data["estado"]))
                    $client->estado = $data["estado"];
                $client->save();
                return response()-> json(["mensaje"=> "Operacion exitosa","siglas"=> "OE"],200);
                
            }else{
                return response()-> json(["mensaje"=> "La data no tiene el formato deseado","siglas"=> "DNF"],400);
            }
            
        }else{
            return response()-> json(["mensaje"=> "No se encontro ningun dato","siglas"=> "NDE"],204);
        }
    }
}
