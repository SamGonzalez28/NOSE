<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Local;
use Illuminate\Http\Request;

/**
 * Description of SecionController
 *
 * @author alejo
 */
class SesionController extends Controller {

    public function guardar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = \App\Models\Cliente::where("clave", $data["clave"])->first();
                $local = \App\Models\Local::where("external_id", $data["id_local"])->first();
                if ($cliente == true && $local == true) {
                    $sesion = new Cartera();
                    $sesion->local()->associate($local);
                    $sesion->cliente()->associate($cliente);
                }
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }

}
