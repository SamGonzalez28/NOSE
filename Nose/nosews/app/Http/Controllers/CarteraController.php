<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Cartera;
use Illuminate\Http\Request;

/**
 * Description of CarteraController
 *
 * @author alejo
 */
class CarteraController extends Controller {

    public function listar() {
        $cliente = \App\Models\Cliente::where("clave", $data["clave"])->first();
        $lista = \App\Models\Cartera::where('id_cliente', $cliente)->get();
        $data = array();
        foreach ($lista as $item) {
            $data[] = ["cliente" => $item->id_cliente, "saldo" => $item->saldo];
        }
        return $data;
    }

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = \App\Models\Cliente::where("external_id", $data["cliente"])->first();
                $local = \App\Models\Local::where("external_id", $data["local"])->first();
                if ($cliente == true && $local == true) {
                    $cartera = new Cartera();
                    $cartera->local()->associate($local);
                    $cartera->cliente()->associate($cliente);
                    $cartera-> saldo = $data["saldo"];
                    $cartera->save();
                    return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
                }else{
                    return response()->json(["mensaje" => "Referencia Incorrecta", "siglas" => "RI"], 400);
                }
                
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }

}
