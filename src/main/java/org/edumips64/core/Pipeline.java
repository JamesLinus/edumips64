package org.edumips64.core;

import org.edumips64.core.is.Instruction;

import java.util.HashMap;
import java.util.Map;

/** A class representing the 5 pipeline stages, containing instructions.
 * Has some convenience methods for dealing with them and making the CPU code
 * a bit simpler.
 */
class Pipeline {
  private Map<CPU.Stage, Instruction> stageInstructionMap;

  Pipeline() {
    stageInstructionMap = new HashMap<>();
    clear();
  }

  boolean isEmptyOrBubble(CPU.Stage stage) {
    return isEmpty(stage) || isBubble(stage);
  }

  boolean isEmpty(CPU.Stage stage) {
    return stageInstructionMap.get(stage) == null;
  }

  boolean isBubble(CPU.Stage stage) {
    return !isEmpty(stage) && stageInstructionMap.get(stage).getName().equals(" ");
  }

  Map<CPU.Stage, Instruction> getInternalRepresentation() {
    return stageInstructionMap;
  }

  Instruction get(CPU.Stage stage) {
    return stageInstructionMap.get(stage);
  }

  /** Removes the instuction at the given stage, returning it. */
  Instruction remove(CPU.Stage stage) {
    Instruction i = get(stage);
    stageInstructionMap.put(stage, null);
    return i;
  }

  /**
   * Shortcuts for the stages.
   */
  Instruction removeIF() {
    return remove(CPU.Stage.IF);
  }
  Instruction removeID() {
    return remove(CPU.Stage.ID);
  }
  Instruction removeEX() {
    return remove(CPU.Stage.EX);
  }
  Instruction removeMEM() {
    return remove(CPU.Stage.MEM);
  }
  Instruction removeWB() {
    return remove(CPU.Stage.WB);
  }
  Instruction IF() {
    return get(CPU.Stage.IF);
  }

  Instruction ID() {
    return get(CPU.Stage.ID);
  }

  Instruction EX() {
    return get(CPU.Stage.EX);
  }

  Instruction MEM() {
    return get(CPU.Stage.MEM);
  }

  Instruction WB() {
    return get(CPU.Stage.WB);
  }

  Instruction setIF(Instruction instruction) {
    return stageInstructionMap.put(CPU.Stage.IF, instruction);
  }

  Instruction setID(Instruction instruction) {
    return stageInstructionMap.put(CPU.Stage.ID, instruction);
  }

  Instruction setEX(Instruction instruction) {
    return stageInstructionMap.put(CPU.Stage.EX, instruction);
  }

  Instruction setMEM(Instruction instruction) {
    return stageInstructionMap.put(CPU.Stage.MEM, instruction);
  }

  Instruction setWB(Instruction instruction) {
    return stageInstructionMap.put(CPU.Stage.WB, instruction);
  }

  void clear() {
    stageInstructionMap.put(CPU.Stage.IF, null);
    stageInstructionMap.put(CPU.Stage.ID, null);
    stageInstructionMap.put(CPU.Stage.EX, null);
    stageInstructionMap.put(CPU.Stage.MEM, null);
    stageInstructionMap.put(CPU.Stage.WB, null);
  }
}
