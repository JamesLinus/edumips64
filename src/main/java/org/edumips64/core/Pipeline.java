package org.edumips64.core;

import org.edumips64.core.is.Instruction;

import java.util.HashMap;
import java.util.Map;

/** A class representing the 5 pipeline stages, containing instructions.
 * Has some convenience methods for dealing with them and making the CPU code
 * a bit simpler.
 */
class Pipeline {
  private Map<CPU.PipeStage, Instruction> stageInstructionMap;

  Pipeline() {
    stageInstructionMap = new HashMap<>();
    clear();
  }

  boolean isEmptyOrBubble(CPU.PipeStage stage) {
    return isEmpty(stage) || isBubble(stage);
  }

  boolean isEmpty(CPU.PipeStage stage) {
    return stageInstructionMap.get(stage) == null;
  }

  boolean isBubble(CPU.PipeStage stage) {
    return !isEmpty(stage) && stageInstructionMap.get(stage).getName().equals(" ");
  }

  Map<CPU.PipeStage, Instruction> getInternalRepresentation() {
    return stageInstructionMap;
  }

  Instruction get(CPU.PipeStage stage) {
    return stageInstructionMap.get(stage);
  }

  /** Removes the instuction at the given stage, returning it. */
  Instruction remove(CPU.PipeStage stage) {
    Instruction i = get(stage);
    stageInstructionMap.put(stage, null);
    return i;
  }

  /**
   * Shortcuts for the stages.
   */
  Instruction removeIF() {
    return remove(CPU.PipeStage.IF);
  }
  Instruction removeID() {
    return remove(CPU.PipeStage.ID);
  }
  Instruction removeEX() {
    return remove(CPU.PipeStage.EX);
  }
  Instruction removeMEM() {
    return remove(CPU.PipeStage.MEM);
  }
  Instruction removeWB() {
    return remove(CPU.PipeStage.WB);
  }
  Instruction IF() {
    return get(CPU.PipeStage.IF);
  }

  Instruction ID() {
    return get(CPU.PipeStage.ID);
  }

  Instruction EX() {
    return get(CPU.PipeStage.EX);
  }

  Instruction MEM() {
    return get(CPU.PipeStage.MEM);
  }

  Instruction WB() {
    return get(CPU.PipeStage.WB);
  }

  Instruction setIF(Instruction instruction) {
    return stageInstructionMap.put(CPU.PipeStage.IF, instruction);
  }

  Instruction setID(Instruction instruction) {
    return stageInstructionMap.put(CPU.PipeStage.ID, instruction);
  }

  Instruction setEX(Instruction instruction) {
    return stageInstructionMap.put(CPU.PipeStage.EX, instruction);
  }

  Instruction setMEM(Instruction instruction) {
    return stageInstructionMap.put(CPU.PipeStage.MEM, instruction);
  }

  Instruction setWB(Instruction instruction) {
    return stageInstructionMap.put(CPU.PipeStage.WB, instruction);
  }

  void clear() {
    stageInstructionMap.put(CPU.PipeStage.IF, null);
    stageInstructionMap.put(CPU.PipeStage.ID, null);
    stageInstructionMap.put(CPU.PipeStage.EX, null);
    stageInstructionMap.put(CPU.PipeStage.MEM, null);
    stageInstructionMap.put(CPU.PipeStage.WB, null);
  }
}
